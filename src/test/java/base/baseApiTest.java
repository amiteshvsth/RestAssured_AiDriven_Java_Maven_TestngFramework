package base;

import clients.BaseApiClient;
import config.AppConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.AllureUtils;
import utils.TraceUtils;

import java.io.File;

public class BaseApiTest {
    protected BaseApiClient apiClient;

    @BeforeSuite
    public void initializeReporting() {
        AllureUtils.initializeContext();
        initializeAllureResultsDirectory();
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = AppConfig.getBaseUrl();
        apiClient = new BaseApiClient();
        AllureUtils.initializeContext();
    }

    @Step("{0}")
    public void logStep(String stepName) {
    }

    protected Response executeGet(String endpoint) {
        logStep("GET: " + endpoint);
        Response response = apiClient.get(endpoint);
        captureTrace("GET", endpoint, response);
        return response;
    }

    protected Response executeGet(String endpoint, Object... pathParams) {
        logStep("GET: " + endpoint);
        Response response = apiClient.get(endpoint, pathParams);
        captureTrace("GET", endpoint, response);
        return response;
    }

    protected Response executePost(String endpoint, Object body) {
        logStep("POST: " + endpoint);
        TraceUtils.startTrace("POST", endpoint);
        Response response = apiClient.post(endpoint, body);
        captureTrace("POST", endpoint, response);
        return response;
    }

    protected Response executePost(String endpoint, Object body, Object... pathParams) {
        logStep("POST: " + endpoint);
        TraceUtils.startTrace("POST", endpoint);
        Response response = apiClient.post(endpoint, body, pathParams);
        captureTrace("POST", endpoint, response);
        return response;
    }

    protected Response executePut(String endpoint, Object body) {
        logStep("PUT: " + endpoint);
        TraceUtils.startTrace("PUT", endpoint);
        Response response = apiClient.put(endpoint, body);
        captureTrace("PUT", endpoint, response);
        return response;
    }

    protected Response executePut(String endpoint, Object body, Object... pathParams) {
        logStep("PUT: " + endpoint);
        TraceUtils.startTrace("PUT", endpoint);
        Response response = apiClient.put(endpoint, body, pathParams);
        captureTrace("PUT", endpoint, response);
        return response;
    }

    protected Response executeDelete(String endpoint) {
        logStep("DELETE: " + endpoint);
        TraceUtils.startTrace("DELETE", endpoint);
        Response response = apiClient.delete(endpoint);
        captureTrace("DELETE", endpoint, response);
        return response;
    }

    protected Response executeDelete(String endpoint, Object... pathParams) {
        logStep("DELETE: " + endpoint);
        TraceUtils.startTrace("DELETE", endpoint);
        Response response = apiClient.delete(endpoint, pathParams);
        captureTrace("DELETE", endpoint, response);
        return response;
    }

    private void captureTrace(String method, String endpoint, Response response) {
        if (response != null) {
            TraceUtils.captureResponseDetails(response);
            TraceUtils.attachFullTrace();
        }
    }

    private void initializeAllureResultsDirectory() {
        File allureDir = new File("allure-results");
        if (!allureDir.exists()) {
            allureDir.mkdirs();
        }
    }
}
