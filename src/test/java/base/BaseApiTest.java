package base;

import utils.ApiHelpers;
import utils.AllureUtils;
import utils.TraceUtils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    protected ApiHelpers apiHelpers;

    @BeforeSuite
    public void initializeReporting() {
        AllureUtils.initializeContext();
        initializeAllureResultsDirectory();
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        apiHelpers = new ApiHelpers();
        AllureUtils.initializeContext();
    }

    @Step("{0}")
    public void logStep(String stepName) {
    }

    protected Response executeGet(String endpoint) {
        logStep("GET: " + endpoint);
        Response response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).get(endpoint);
        captureTrace("GET", endpoint, response);
        return response;
    }

    protected Response executeGet(String endpoint, Object... pathParams) {
        logStep("GET: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).pathParam("petId", pathParams[0]).get(endpoint);
        } else {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).get(endpoint);
        }
        captureTrace("GET", endpoint, response);
        return response;
    }

    protected Response executePost(String endpoint, Object body) {
        logStep("POST: " + endpoint);
        TraceUtils.startTrace("POST", endpoint);
        Response response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).post(endpoint);
        captureTrace("POST", endpoint, response);
        return response;
    }

    protected Response executePost(String endpoint, Object body, Object... pathParams) {
        logStep("POST: " + endpoint);
        TraceUtils.startTrace("POST", endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).pathParam("petId", pathParams[0]).post(endpoint);
        } else {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).post(endpoint);
        }
        captureTrace("POST", endpoint, response);
        return response;
    }

    protected Response executePut(String endpoint, Object body) {
        logStep("PUT: " + endpoint);
        TraceUtils.startTrace("PUT", endpoint);
        Response response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).put(endpoint);
        captureTrace("PUT", endpoint, response);
        return response;
    }

    protected Response executePut(String endpoint, Object body, Object... pathParams) {
        logStep("PUT: " + endpoint);
        TraceUtils.startTrace("PUT", endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).pathParam("petId", pathParams[0]).put(endpoint);
        } else {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).body(body).put(endpoint);
        }
        captureTrace("PUT", endpoint, response);
        return response;
    }

    protected Response executeDelete(String endpoint) {
        logStep("DELETE: " + endpoint);
        TraceUtils.startTrace("DELETE", endpoint);
        Response response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).delete(endpoint);
        captureTrace("DELETE", endpoint, response);
        return response;
    }

    protected Response executeDelete(String endpoint, Object... pathParams) {
        logStep("DELETE: " + endpoint);
        TraceUtils.startTrace("DELETE", endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).pathParam("petId", pathParams[0]).delete(endpoint);
        } else {
            response = given().spec(apiHelpers.requestSpecificationWithJSONHeader()).delete(endpoint);
        }
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
