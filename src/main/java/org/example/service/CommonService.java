package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class CommonService {
    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    private final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        this.requestSpecification = RestAssured.given().headers(headers);
    }

    protected Response getRequest(String uri, int expectedStatusCode) {
        Log.info("Sending the GET request to the Uri: " + prepareUri.apply(uri));
        Response response = requestSpecification.expect().statusCode(expectedStatusCode).log().ifError()
                .when().get(prepareUri.apply(uri));
        Log.info("Response body is: \n" + response.asPrettyString() + "\n");
        Log.info("Response headers are: \n" + response.getHeaders() + "\n");
        return response;
    }

    protected Response postRequest(String uri, Object body, int expectedStatusCode) {
        Log.info("Sending the POST request to the Uri: " + prepareUri.apply(uri));
        Response response = requestSpecification.body(body).expect().statusCode(expectedStatusCode).log().ifError()
                .when().post(prepareUri.apply(uri));
        Log.info("Response body is: \n" + response.asPrettyString() + "\n");
        Log.info("Response headers are: \n" + response.getHeaders() + "\n");

        return response;
    }

    protected Response deleteRequest(String uri, int expectedStatusCode) {
        Log.info("Sending the DELETE request to the Uri: " + prepareUri.apply(uri));
        Response response = requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
                .when().delete(prepareUri.apply(uri));
        Log.info("Response body is: \n" + response.asPrettyString() + "\n");
        Log.info("Response headers are: \n" + response.getHeaders() + "\n");

        return response;
    }
}
