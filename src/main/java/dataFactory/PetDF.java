package dataFactory;

import com.github.javafaker.Faker;
import dto.pet.AddPetRequest;
import dto.pet.CategoryRequest;
import dto.pet.TagRequest;
import dto.pet.UpdatePetRequest;

import java.util.ArrayList;
import java.util.List;

public class PetDF {

    private static final Faker faker = new Faker();

    public static AddPetRequest getData() {
        AddPetRequest request = new AddPetRequest();
        request.setId(faker.number().randomNumber());
        request.setName(faker.animal().name());

        CategoryRequest category = new CategoryRequest();
        category.setId(faker.number().randomNumber());
        category.setName(faker.commerce().productName());
        request.setCategory(category);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());
        photoUrls.add(faker.internet().url());
        request.setPhotoUrls(photoUrls);

        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag1 = new TagRequest();
        tag1.setId(faker.number().randomNumber());
        tag1.setName(faker.color().name().toLowerCase());
        tags.add(tag1);

        TagRequest tag2 = new TagRequest();
        tag2.setId(faker.number().randomNumber());
        tag2.setName(faker.commerce().material());
        tags.add(tag2);
        request.setTags(tags);

        request.setStatus("available");
        return request;
    }

    public static UpdatePetRequest getUpdateData() {
        UpdatePetRequest request = new UpdatePetRequest();
        request.setId(faker.number().randomNumber());
        request.setName(faker.animal().name());

        CategoryRequest category = new CategoryRequest();
        category.setId(faker.number().randomNumber());
        category.setName(faker.commerce().productName());
        request.setCategory(category);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());
        request.setPhotoUrls(photoUrls);

        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag = new TagRequest();
        tag.setId(faker.number().randomNumber());
        tag.setName(faker.color().name().toLowerCase());
        tags.add(tag);
        request.setTags(tags);

        request.setStatus("pending");
        return request;
    }

    public static Long getValidId() {
        return faker.number().randomNumber();
    }

    public static Long getNonexistentId() {
        return 999999999L;
    }

    public static Long getInvalidId() {
        return -1L;
    }

    public static String getAvailableStatus() {
        return "available";
    }

    public static String getInvalidStatus() {
        return "invalid_status";
    }

    public static String getValidTag() {
        return faker.color().name().toLowerCase();
    }
}
