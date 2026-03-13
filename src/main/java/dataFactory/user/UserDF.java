package dataFactory.user;

import dto.request.user.CreateUserRequest;
import dto.request.user.CreateUsersWithArrayRequest;
import dto.request.user.CreateUsersWithListRequest;
import dto.request.user.UpdateUserRequest;

import java.util.Arrays;
import java.util.List;

public class UserDF {

    public static CreateUsersWithArrayRequest createValidCreateUsersWithArrayRequest() {
        return CreateUsersWithArrayRequest.builder()
                .users(createMultipleUserRequests())
                .build();
    }

    public static CreateUsersWithListRequest createValidCreateUsersWithListRequest() {
        return CreateUsersWithListRequest.builder()
                .users(createMultipleUserRequests())
                .build();
    }

    public static CreateUserRequest createValidCreateUserRequest() {
        return CreateUserRequest.builder()
                .id(1L)
                .username("johndoe")
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .password("password123")
                .phone("1234567890")
                .userStatus(1)
                .build();
    }

    public static CreateUserRequest createCreateUserRequestWithMinimalData() {
        return CreateUserRequest.builder()
                .username("janedoe")
                .email("janedoe@example.com")
                .password("password123")
                .build();
    }

    public static CreateUserRequest createCreateUserRequestWithInvalidEmail() {
        return CreateUserRequest.builder()
                .username("testuser")
                .email("invalid-email")
                .password("password123")
                .build();
    }

    public static CreateUserRequest createCreateUserRequestWithEmptyPayload() {
        return CreateUserRequest.builder().build();
    }

    public static UpdateUserRequest createValidUpdateUserRequest() {
        return UpdateUserRequest.builder()
                .id(1L)
                .username("johndoe")
                .firstName("JohnUpdated")
                .lastName("DoeUpdated")
                .email("johndoe.updated@example.com")
                .password("newpassword456")
                .phone("0987654321")
                .userStatus(1)
                .build();
    }

    public static List<CreateUserRequest> createMultipleUserRequests() {
        return Arrays.asList(
                CreateUserRequest.builder()
                        .id(1L)
                        .username("user1")
                        .firstName("User")
                        .lastName("One")
                        .email("user1@example.com")
                        .password("password1")
                        .build(),
                CreateUserRequest.builder()
                        .id(2L)
                        .username("user2")
                        .firstName("User")
                        .lastName("Two")
                        .email("user2@example.com")
                        .password("password2")
                        .build(),
                CreateUserRequest.builder()
                        .id(3L)
                        .username("user3")
                        .firstName("User")
                        .lastName("Three")
                        .email("user3@example.com")
                        .password("password3")
                        .build()
        );
    }

    public static UpdateUserRequest createUpdateUserRequestWithInvalidEmail() {
        return UpdateUserRequest.builder()
                .username("johndoe")
                .email("invalid-email-format")
                .password("password123")
                .build();
    }

    public static UpdateUserRequest createUpdateUserRequestWithEmptyPayload() {
        return UpdateUserRequest.builder().build();
    }

    public static String getValidUsername() {
        return "johndoe";
    }

    public static String getNonexistentUsername() {
        return "nonexistentuser12345";
    }

    public static String getInvalidUsername() {
        return "!!invalid";
    }

    public static String getNonexistentUserForUpdate() {
        return "nonexistentuser999";
    }

    public static String getValidLoginUsername() {
        return "johndoe";
    }

    public static String getValidLoginPassword() {
        return "password123";
    }
}
