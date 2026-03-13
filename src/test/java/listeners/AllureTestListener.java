package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.testng.*;
import utils.AllureUtils;
import utils.ScreenshotUtils;
import utils.TraceUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllureTestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final Map<Long, String> testNames = new ConcurrentHashMap<>();

    @Override
    public void onStart(ISuite suite) {
        AllureUtils.initializeContext();
        initializeAllureResultsDirectory();
    }

    @Override
    public void onFinish(ISuite suite) {
        AllureUtils.clearContext();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        testNames.put(Thread.currentThread().threadId(), testName);

        AllureUtils.initializeContext();

        Allure.getLifecycle().startTestCase(result.getTestContext().getCurrentXmlTest().getName() + 
                "." + testName);

        AllureUtils.addLabel("testClass", result.getTestClass().getRealClass().getSimpleName());
        AllureUtils.addLabel("testMethod", testName);
        AllureUtils.addTestGroupsAsLabels(result);

        addTestCategories(result);
        addSeverityLabel(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setStatus(Status.PASSED);
        });
        String successTestName = testNames.get(Thread.currentThread().threadId());
        Allure.getLifecycle().stopTestCase(successTestName);
        TraceUtils.clearTrace();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = testNames.get(Thread.currentThread().threadId());

        ScreenshotUtils.attachScreenshotOnFailure(testName, result.getThrowable());

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            String failureMessage = throwable.getMessage() != null ? 
                    throwable.getMessage() : "Test failed with no message";
            String stackTrace = getStackTraceAsString(throwable);
            AllureUtils.setTestStatusDetails(failureMessage, stackTrace);
        }

        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setStatus(Status.FAILED);
        });
        Allure.getLifecycle().stopTestCase(testName);
        TraceUtils.clearTrace();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setStatus(Status.SKIPPED);
        });
        String skippedTestName = testNames.get(Thread.currentThread().threadId());
        if (skippedTestName != null) {
            Allure.getLifecycle().stopTestCase(skippedTestName);
        }
        TraceUtils.clearTrace();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setStatus(Status.BROKEN);
        });
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    private void initializeAllureResultsDirectory() {
        File allureDir = new File("allure-results");
        if (!allureDir.exists()) {
            allureDir.mkdirs();
        }
        File screenshotDir = new File("allure-results/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    private void addTestCategories(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        Arrays.stream(groups).forEach(group -> {
            if (isSmokeGroup(group)) {
                AllureUtils.addCategoryLabel("Smoke");
            } else if (isRegressionGroup(group)) {
                AllureUtils.addCategoryLabel("Regression");
            }
        });
    }

    private boolean isSmokeGroup(String group) {
        return "smoke".equalsIgnoreCase(group);
    }

    private boolean isRegressionGroup(String group) {
        return "regression".equalsIgnoreCase(group);
    }

    private void addSeverityLabel(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        if (Arrays.asList(groups).contains("smoke")) {
            AllureUtils.addSeverityLabel("critical");
        } else {
            AllureUtils.addSeverityLabel("normal");
        }
    }

    private String getStackTraceAsString(Throwable throwable) {
        if (throwable == null) return "";
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
