package org.example.steps;

import org.example.entities.User;
import org.example.entities.UserResponse;
import org.example.service.UserService;
import org.example.service.uritemplate.UriTemplate;

import java.util.List;

import static org.example.service.uritemplate.UserServiceUri.USER_BY_ID;
import static org.example.service.uritemplate.UserServiceUri.USER_CREATE_WITH_ARRAY;

public class UserServiceSteps {

    private static final UserService USER_SERVICE = UserService.getInstance();

    public static UserResponse createUser(List<User> expectedUser, int expectedStatusCode) {
        return USER_SERVICE.postRequest(USER_CREATE_WITH_ARRAY, expectedUser, expectedStatusCode).as(UserResponse.class);
    }

    public static User getUser(String userId, int expectedStatusCode) {
        return USER_SERVICE.getRequest(new UriTemplate(USER_BY_ID.getUri() + userId), expectedStatusCode).as(User.class);
    }

    public static UserResponse deleteUser(String userId, int expectedStatusCode) {
        return USER_SERVICE.deleteRequest(new UriTemplate(USER_BY_ID.getUri() + userId), expectedStatusCode).as(UserResponse.class);
    }
}
