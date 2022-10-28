package org.example.service;

import io.restassured.response.Response;
import org.example.service.uritemplate.UriTemplate;

public class UserService extends CommonService {

    private UserService() {
    }

    //default constructor
    private static UserService instance;

    //singleton; saves, creates
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, int expectedStatusCode) {
        return super.getRequest(uri.getUri(), expectedStatusCode);
    }

    public Response postRequest(UriTemplate uri, Object body, int expectedStatusCode) {
        return super.postRequest(uri.getUri(), body, expectedStatusCode);
    }

    public Response deleteRequest(UriTemplate uri, int expectedStatusCode) {
        return super.deleteRequest(uri.getUri(), expectedStatusCode);
    }
}
