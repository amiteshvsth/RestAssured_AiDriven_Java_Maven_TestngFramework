package dataFactory;

import com.github.javafaker.Faker;
import dto.user.CreateUserRequest;

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
}