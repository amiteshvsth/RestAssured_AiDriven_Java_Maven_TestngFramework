package clients;

import builders.RequestBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Logger;

public class BaseApiClient {
    private final RequestSpecification baseSpec;

    public BaseApiClient() {
        baseSpec = new RequestBuilder().build();
    }

    public Response get(String endpoint) {
        Logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.get(endpoint);
        Logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response get(String endpoint, Object... pathParams) {
        Logger.info("Executing GET request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.pathParam("petId", pathParams[0]).get(endpoint);
        } else {
            response = baseSpec.get(endpoint);
        }
        Logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithQueryParams(String endpoint, String queryKey1, Object queryValue1) {
        Logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.queryParam(queryKey1, queryValue1).get(endpoint);
        Logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithQueryParams(String endpoint, String queryKey1, Object queryValue1, String queryKey2, Object queryValue2) {
        Logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.queryParam(queryKey1, queryValue1).queryParam(queryKey2, queryValue2).get(endpoint);
        Logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response post(String endpoint, Object body) {
        Logger.info("Executing POST request to: " + endpoint);
        Response response = baseSpec.body(body).post(endpoint);
        Logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response put(String endpoint, Object body) {
        Logger.info("Executing PUT request to: " + endpoint);
        Response response = baseSpec.body(body).put(endpoint);
        Logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response put(String endpoint, Object body, Object... pathParams) {
        Logger.info("Executing PUT request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.body(body).pathParam("petId", pathParams[0]).put(endpoint);
        } else {
            response = baseSpec.body(body).put(endpoint);
        }
        Logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response post(String endpoint, Object body, Object... pathParams) {
        Logger.info("Executing POST request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.body(body).pathParam("petId", pathParams[0]).post(endpoint);
        } else {
            response = baseSpec.body(body).post(endpoint);
        }
        Logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response delete(String endpoint) {
        Logger.info("Executing DELETE request to: " + endpoint);
        Response response = baseSpec.delete(endpoint);
        Logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }

    public Response delete(String endpoint, Object... pathParams) {
        Logger.info("Executing DELETE request to: " + endpoint);
        Response response;
        if (pathParams != null && pathParams.length > 0) {
            response = baseSpec.pathParam("petId", pathParams[0]).delete(endpoint);
        } else {
            response = baseSpec.delete(endpoint);
        }
        Logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }

    public Response getWithPathParam(String endpoint, String paramName, Object paramValue) {
        Logger.info("Executing GET request to: " + endpoint);
        Response response = baseSpec.pathParam(paramName, paramValue).get(endpoint);
        Logger.info("GET response status: " + response.getStatusCode());
        return response;
    }

    public Response putWithPathParam(String endpoint, Object body, String paramName, Object paramValue) {
        Logger.info("Executing PUT request to: " + endpoint);
        Response response = baseSpec.body(body).pathParam(paramName, paramValue).put(endpoint);
        Logger.info("PUT response status: " + response.getStatusCode());
        return response;
    }

    public Response postWithPathParam(String endpoint, Object body, String paramName, Object paramValue) {
        Logger.info("Executing POST request to: " + endpoint);
        Response response = baseSpec.body(body).pathParam(paramName, paramValue).post(endpoint);
        Logger.info("POST response status: " + response.getStatusCode());
        return response;
    }

    public Response deleteWithPathParam(String endpoint, String paramName, Object paramValue) {
        Logger.info("Executing DELETE request to: " + endpoint);
        Response response = baseSpec.pathParam(paramName, paramValue).delete(endpoint);
        Logger.info("DELETE response status: " + response.getStatusCode());
        return response;
    }
}
