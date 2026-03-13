package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AllureUtils {

    private static final Map<Long, Map<String, String>> threadContext = new ConcurrentHashMap<>();

    public static void initializeContext() {
        Long threadId = Thread.currentThread().threadId();
        Map<String, String> context = new HashMap<>();
        context.put("testId", UUID.randomUUID().toString());
        context.put("threadId", String.valueOf(threadId));
        threadContext.put(threadId, context);
    }

    public static void clearContext() {
        threadContext.remove(Thread.currentThread().threadId());
    }

    public static String getTestId() {
        Map<String, String> context = threadContext.get(Thread.currentThread().threadId());
        return context != null ? context.get("testId") : UUID.randomUUID().toString();
    }

    public static void addLabel(String name, String value) {
        Allure.getLifecycle().updateTestCase(result -> {
            result.getLabels().add(new io.qameta.allure.model.Label().setName(name).setValue(value));
        });
    }

    public static void addLabels(Map<String, String> labels) {
        labels.forEach(AllureUtils::addLabel);
    }

    public static void addFeatureLabel(String feature) {
        addLabel("feature", feature);
    }

    public static void addStoryLabel(String story) {
        addLabel("story", story);
    }

    public static void addSeverityLabel(String severity) {
        addLabel("severity", severity);
    }

    public static void addModuleLabel(String module) {
        addLabel("module", module);
    }

    public static void addCategoryLabel(String category) {
        addLabel("category", category);
    }

    public static void addTestGroupsAsLabels(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        Arrays.stream(groups).forEach(group -> {
            if (isModuleGroup(group)) {
                addModuleLabel(group);
            } else if (isCategoryGroup(group)) {
                addCategoryLabel(group);
            }
        });
    }

    private static boolean isModuleGroup(String group) {
        return Arrays.asList("pet", "store", "user").contains(group.toLowerCase());
    }

    private static boolean isCategoryGroup(String group) {
        return Arrays.asList("smoke", "regression", "positive", "negative", "edge").contains(group.toLowerCase());
    }

    public static void addDescription(String description) {
        Allure.getLifecycle().updateTestCase(result -> result.setDescription(description));
    }

    public static void addLink(String name, String url, String linkType) {
        Allure.getLifecycle().updateTestCase(result -> {
            io.qameta.allure.model.Link link = new io.qameta.allure.model.Link();
            link.setName(name);
            link.setUrl(url);
            link.setType(linkType);
            result.getLinks().add(link);
        });
    }

    public static void addAttachment(String name, String content, String mimeType) {
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        Allure.getLifecycle().addAttachment(name, mimeType, null, inputStream);
    }

    public static void addAttachment(String name, InputStream content, String mimeType) {
        Allure.getLifecycle().addAttachment(name, mimeType, null, content);
    }

    public static void addAttachment(String name, byte[] content, String mimeType) {
        InputStream inputStream = new ByteArrayInputStream(content);
        Allure.getLifecycle().addAttachment(name, mimeType, null, inputStream);
    }

    public static void addJsonAttachment(String name, String json) {
        addAttachment(name, json, "application/json");
    }

    public static void addXmlAttachment(String name, String xml) {
        addAttachment(name, xml, "application/xml");
    }

    public static void addTextAttachment(String name, String text) {
        addAttachment(name, text, "text/plain");
    }

    public static void addHtmlAttachment(String name, String html) {
        addAttachment(name, html, "text/html");
    }

    public static void logStep(String stepName) {
        Allure.getLifecycle().startStep(stepName, new StepResult().setName(stepName).setStatus(Status.PASSED));
    }

    public static void logStep(String stepName, Status status) {
        Allure.getLifecycle().startStep(stepName, new StepResult().setName(stepName).setStatus(status));
    }

    public static void stopStep() {
        Allure.getLifecycle().stopStep();
    }

    public static void setTestStatus(Status status) {
        Allure.getLifecycle().updateTestCase(result -> result.setStatus(status));
    }

    public static void setTestStatusDetails(String message, String trace) {
        Allure.getLifecycle().updateTestCase(result -> {
            result.setStatus(Status.FAILED);
            result.setStatusDetails(new io.qameta.allure.model.StatusDetails()
                    .setMessage(message)
                    .setTrace(trace));
        });
    }

    public static void addEnvironmentInfo(String key, String value) {
        Allure.getLifecycle().updateTestCase(result -> {
            result.getLabels().add(new io.qameta.allure.model.Label()
                    .setName("environment")
                    .setValue(key + "=" + value));
        });
    }
}
