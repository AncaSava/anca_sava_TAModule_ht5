package org.example.userstest;

import org.apache.http.HttpStatus;
import org.example.entities.User;
import org.example.entities.UserResponse;
import org.example.steps.UserServiceSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserServiceTest {


    @Test
    public void createUserTest() {

        //create a user
        List<User> expectedUser = createUser();
        UserResponse actualUser = UserServiceSteps.createUser(expectedUser, HttpStatus.SC_OK);
        Assert.assertEquals(actualUser.getCode(), HttpStatus.SC_OK, "Expected status code not equal to actual");
    }

    @Test
    public void getUserTest() {

        //create a user
        List<User> createdUser = createUser();
        UserResponse actualUser = UserServiceSteps.createUser(createdUser, HttpStatus.SC_OK);
        Assert.assertEquals(actualUser.getCode(), HttpStatus.SC_OK, "Expected status code not equal to actual");

        //retrieve created user
        User userName = UserServiceSteps.getUser(createdUser.get(0).getUsername(), HttpStatus.SC_OK);
        Assert.assertEquals(createdUser.get(0).getUsername(), userName.getUsername(), "Expected userName not equal to actual");
    }

    @Test
    public void deleteUserTest() {

        //create a user
        List<User> createdUser = createUser();
        UserResponse actualUser = UserServiceSteps.createUser(createdUser, HttpStatus.SC_OK);
        Assert.assertEquals(actualUser.getCode(), HttpStatus.SC_OK, "Expected status code not equal to actual");

        //retrieve created user
        User userName = UserServiceSteps.getUser(createdUser.get(0).getUsername(), HttpStatus.SC_OK);
        Assert.assertEquals(createdUser.get(0).getUsername(), userName.getUsername(), "Expected userName not equal to actual");

        //delete user
        UserResponse deleteUser = UserServiceSteps.deleteUser(createdUser.get(0).getUsername(), HttpStatus.SC_OK);
        Assert.assertEquals(deleteUser.getCode(), HttpStatus.SC_OK, "Expected userName not equal to actual");

        //try to retrieve the deleted user
        User deletedUser = UserServiceSteps.getUser(createdUser.get(0).getUsername(), HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(deletedUser.getMessage(), "User not found", "Expected userName not equal to actual");

    }


    private List<User> createUser() {
        Random random = new Random();
        List<User> userList = new ArrayList<>();
        User user = new User()
                .setId(random.nextInt(999))
                .setUsername("JohnDoe" + random.nextInt(999))
                .setFirstName("John" + random.nextInt(999))
                .setLastName("Doe" + random.nextInt(999))
                .setEmail("testEmail" + random.nextInt(999) + "@test.com")
                .setPassword("password" + random.nextInt(999))
                .setPhone("phone" + random.nextInt(999999999))
                .setUserStatus(random.nextInt(999));
        userList.add(user);

        return userList;
    }
}
