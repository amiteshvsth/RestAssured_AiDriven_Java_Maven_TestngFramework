package dataFactory;

import com.github.javafaker.Faker;
import dto.user.CreateUserRequest;
import dto.user.CreateUsersWithArrayRequest;
import dto.user.CreateUsersWithListRequest;
import dto.user.UpdateUserRequest;

import java.util.ArrayList;
import java.util.List;

public class UserDF {

    private static final Faker faker = new Faker();

    public static CreateUserRequest getData() {
        CreateUserRequest request = new CreateUserRequest();
        request.setId(faker.number().randomNumber());
        request.setUsername(faker.name().username().replace(".", "").toLowerCase());
        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword(faker.internet().password(8, 16));
        request.setPhone(faker.phoneNumber().cellPhone());
        request.setUserStatus(1);
        return request;
    }

    public static UpdateUserRequest getUpdateData() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(faker.number().randomNumber());
        request.setUsername(faker.name().username().replace(".", "").toLowerCase());
        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword(faker.internet().password(8, 16));
        request.setPhone(faker.phoneNumber().cellPhone());
        request.setUserStatus(1);
        return request;
    }

    public static CreateUsersWithArrayRequest getArrayData() {
        CreateUsersWithArrayRequest request = new CreateUsersWithArrayRequest();
        request.setUsers(createMultipleUserRequests());
        return request;
    }

    public static CreateUsersWithListRequest getListData() {
        CreateUsersWithListRequest request = new CreateUsersWithListRequest();
        request.setUsers(createMultipleUserRequests());
        return request;
    }

    private static List<CreateUserRequest> createMultipleUserRequests() {
        List<CreateUserRequest> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CreateUserRequest request = new CreateUserRequest();
            request.setId(faker.number().randomNumber());
            request.setUsername(faker.name().username().replace(".", "").toLowerCase());
            request.setFirstName(faker.name().firstName());
            request.setLastName(faker.name().lastName());
            request.setEmail(faker.internet().emailAddress());
            request.setPassword(faker.internet().password(8, 16));
            users.add(request);
        }
        return users;
    }

    public static String getValidUsername() {
        return faker.name().username().replace(".", "").toLowerCase();
    }

    public static String getNonexistentUsername() {
        return "nonexistentuser" + faker.number().randomNumber();
    }

    public static String getInvalidUsername() {
        return "!!invalid";
    }

    public static String getValidPassword() {
        return faker.internet().password(8, 16);
    }
}
