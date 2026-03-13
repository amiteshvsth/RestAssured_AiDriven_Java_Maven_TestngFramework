package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TraceUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<Long, RequestTrace> currentTraces = new ConcurrentHashMap<>();

    public static class RequestTrace {
        public String method;
        public String endpoint;
        public Map<String, String> headers;
        public String requestBody;
        public String requestUrl;
        public int statusCode;
        public String responseBody;
        public Map<String, String> responseHeaders;
        public long responseTime;
        public long timestamp;

        public RequestTrace() {
            this.timestamp = System.currentTimeMillis();
            this.headers = new HashMap<>();
            this.responseHeaders = new HashMap<>();
        }
    }

    public static void startTrace(String method, String endpoint) {
        RequestTrace trace = new RequestTrace();
        trace.method = method;
        trace.endpoint = endpoint;
        currentTraces.put(Thread.currentThread().threadId(), trace);
    }

    public static void captureRequestDetails(RequestSpecification requestSpec) {
        Long threadId = Thread.currentThread().threadId();
        RequestTrace trace = currentTraces.get(threadId);
        if (trace == null) {
            trace = new RequestTrace();
            currentTraces.put(threadId, trace);
        }

        if (requestSpec instanceof FilterableRequestSpecification) {
            FilterableRequestSpecification filterableSpec = (FilterableRequestSpecification) requestSpec;
            trace.method = filterableSpec.getMethod();
            trace.requestUrl = filterableSpec.getURI();
            trace.endpoint = filterableSpec.getBasePath();

            Headers headers = filterableSpec.getHeaders();
            if (headers != null) {
                for (Header header : headers) {
                    trace.headers.put(header.getName(), header.getValue());
                }
            }

            if (filterableSpec.getBody() != null) {
                trace.requestBody = filterableSpec.getBody().toString();
            }
        }
    }

    public static void captureResponseDetails(Response response) {
        Long threadId = Thread.currentThread().threadId();
        RequestTrace trace = currentTraces.get(threadId);
        if (trace == null) {
            trace = new RequestTrace();
            currentTraces.put(threadId, trace);
        }

        trace.statusCode = response.getStatusCode();
        trace.responseBody = response.getBody().asString();
        trace.responseTime = response.getTimeIn(java.util.concurrent.TimeUnit.MILLISECONDS);

        Headers headers = response.getHeaders();
        if (headers != null) {
            for (Header header : headers) {
                trace.responseHeaders.put(header.getName(), header.getValue());
            }
        }
    }

    public static void attachRequestTrace() {
        Long threadId = Thread.currentThread().threadId();
        RequestTrace trace = currentTraces.get(threadId);
        if (trace == null) return;

        StringBuilder traceLog = new StringBuilder();
        traceLog.append("=== REQUEST TRACE ===\n");
        traceLog.append(String.format("Method: %s\n", trace.method));
        traceLog.append(String.format("Endpoint: %s\n", trace.endpoint));
        traceLog.append(String.format("URL: %s\n", trace.requestUrl));
        traceLog.append(String.format("Timestamp: %d\n\n", trace.timestamp));

        traceLog.append("--- Request Headers ---\n");
        if (trace.headers != null && !trace.headers.isEmpty()) {
            trace.headers.forEach((key, value) -> traceLog.append(String.format("%s: %s\n", key, value)));
        } else {
            traceLog.append("No headers\n");
        }

        traceLog.append("\n--- Request Body ---\n");
        traceLog.append(trace.requestBody != null ? formatJson(trace.requestBody) : "No body");

        AllureUtils.addTextAttachment("Request_Trace", traceLog.toString());
    }

    public static void attachResponseTrace() {
        Long threadId = Thread.currentThread().threadId();
        RequestTrace trace = currentTraces.get(threadId);
        if (trace == null) return;

        StringBuilder traceLog = new StringBuilder();
        traceLog.append("=== RESPONSE TRACE ===\n");
        traceLog.append(String.format("Status Code: %d\n", trace.statusCode));
        traceLog.append(String.format("Response Time: %d ms\n", trace.responseTime));
        traceLog.append(String.format("Timestamp: %d\n\n", trace.timestamp));

        traceLog.append("--- Response Headers ---\n");
        if (trace.responseHeaders != null && !trace.responseHeaders.isEmpty()) {
            trace.responseHeaders.forEach((key, value) -> traceLog.append(String.format("%s: %s\n", key, value)));
        } else {
            traceLog.append("No headers\n");
        }

        traceLog.append("\n--- Response Body ---\n");
        traceLog.append(trace.responseBody != null ? formatJson(trace.responseBody) : "No body");

        AllureUtils.addTextAttachment("Response_Trace", traceLog.toString());
    }

    public static void attachFullTrace() {
        Long threadId = Thread.currentThread().threadId();
        RequestTrace trace = currentTraces.get(threadId);
        if (trace == null) return;

        StringBuilder fullTrace = new StringBuilder();

        fullTrace.append("╔════════════════════════════════════════════════════════════╗\n");
        fullTrace.append("║                    API CALL TRACE                         ║\n");
        fullTrace.append("╚════════════════════════════════════════════════════════════╝\n\n");

        fullTrace.append("REQUEST\n");
        fullTrace.append("─────────────────────────────────────────────────────────────\n");
        fullTrace.append(String.format("  Method      : %s\n", trace.method));
        fullTrace.append(String.format("  Endpoint    : %s\n", trace.endpoint));
        fullTrace.append(String.format("  Full URL    : %s\n", trace.requestUrl));
        fullTrace.append(String.format("  Time        : %d\n", trace.timestamp));

        fullTrace.append("\n  Headers:\n");
        if (trace.headers != null && !trace.headers.isEmpty()) {
            trace.headers.forEach((key, value) -> 
                fullTrace.append(String.format("    %s: %s\n", key, value)));
        } else {
            fullTrace.append("    (none)\n");
        }

        fullTrace.append("\n  Body:\n");
        fullTrace.append("    ").append(trace.requestBody != null ? 
            formatJson(trace.requestBody).replace("\n", "\n    ") : "(empty)");

        fullTrace.append("\n\nRESPONSE\n");
        fullTrace.append("─────────────────────────────────────────────────────────────\n");
        fullTrace.append(String.format("  Status Code : %d\n", trace.statusCode));
        fullTrace.append(String.format("  Time        : %d ms\n", trace.responseTime));

        fullTrace.append("\n  Headers:\n");
        if (trace.responseHeaders != null && !trace.responseHeaders.isEmpty()) {
            trace.responseHeaders.forEach((key, value) -> 
                fullTrace.append(String.format("    %s: %s\n", key, value)));
        } else {
            fullTrace.append("    (none)\n");
        }

        fullTrace.append("\n  Body:\n");
        fullTrace.append("    ").append(trace.responseBody != null ? 
            formatJson(trace.responseBody).replace("\n", "\n    ") : "(empty)");

        AllureUtils.addTextAttachment("Full_API_Trace", fullTrace.toString());
        
        clearTrace();
    }

    public static void clearTrace() {
        currentTraces.remove(Thread.currentThread().threadId());
    }

    public static void attachCurrentRequest(RequestSpecification requestSpec) {
        captureRequestDetails(requestSpec);
        attachRequestTrace();
    }

    public static void attachCurrentResponse(Response response) {
        captureResponseDetails(response);
        attachResponseTrace();
    }

    private static String formatJson(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return jsonString;
        }
        try {
            Object json = objectMapper.readValue(jsonString, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (Exception e) {
        return jsonString;
    }
}
}
