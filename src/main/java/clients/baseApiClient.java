package clients;

import builders.requestBuilder;
import config.appConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.logger;

public class baseApiClient {
    private final RequestSpecification baseSpec;

    public baseApiClient() {
        baseSpec = new requestBuilder().build();
        baseSpec.baseUri(appConfig.getBaseUrl());
    }

    public Response get(String endpoint) {
        logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.get(endpoint);
        logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response get(String endpoint, Object... pathParams) {
        logger.info("Executing GET request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.pathParam("petId", pathParams[0]).get(endpoint);
        } else {
            response = baseSpec.get(endpoint);
        }
        logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithQueryParams(String endpoint, String queryKey1, Object queryValue1) {
        logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.queryParam(queryKey1, queryValue1).get(endpoint);
        logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithQueryParams(String endpoint, String queryKey1, Object queryValue1, String queryKey2, Object queryValue2) {
        logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.queryParam(queryKey1, queryValue1).queryParam(queryKey2, queryValue2).get(endpoint);
        logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response post(String endpoint, Object body) {
        logger.info("Executing POST request to: " + endpoint);
        Response response = baseSpec.body(body).post(endpoint);
        logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response put(String endpoint, Object body) {
        logger.info("Executing PUT request to: " + endpoint);
        Response response = baseSpec.body(body).put(endpoint);
        logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response put(String endpoint, Object body, Object... pathParams) {
        logger.info("Executing PUT request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.body(body).pathParam("petId", pathParams[0]).put(endpoint);
        } else {
            response = baseSpec.body(body).put(endpoint);
        }
        logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response post(String endpoint, Object body, Object... pathParams) {
        logger.info("Executing POST request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.body(body).pathParam("petId", pathParams[0]).post(endpoint);
        } else {
            response = baseSpec.body(body).post(endpoint);
        }
        logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response delete(String endpoint) {
        logger.info("Executing DELETE request to: " + endpoint);
        Response response = baseSpec.delete(endpoint);
        logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }

    public Response delete(String endpoint, Object... pathParams) {
        logger.info("Executing DELETE request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.pathParam("petId", pathParams[0]).delete(endpoint);
        } else {
            response = baseSpec.delete(endpoint);
        }
        logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithPathParam(String endpoint, String paramName, Object paramValue) {
        logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.pathParam(paramName, paramValue).get(endpoint);
        logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response putWithPathParam(String endpoint, Object body, String paramName, Object paramValue) {
        logger.info("Executing PUT request to: " + endpoint);
        Response response = baseSpec.body(body).pathParam(paramName, paramValue).put(endpoint);
        logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response postWithPathParam(String endpoint, Object body, String paramName, Object paramValue) {
        logger.info("Executing POST request to: " + endpoint);
        Response response = baseSpec.body(body).pathParam(paramName, paramValue).post(endpoint);
        logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response deleteWithPathParam(String endpoint, String paramName, Object paramValue) {
        logger.info("Executing DELETE request to: " + endpoint);
        Response response = baseSpec.pathParam(paramName, paramValue).delete(endpoint);
        logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }
}
