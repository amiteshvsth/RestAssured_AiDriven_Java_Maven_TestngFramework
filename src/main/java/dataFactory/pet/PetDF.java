package dataFactory.pet;

import dto.pet.AddPetRequest;
import dto.pet.Category;
import dto.pet.Tag;
import dto.pet.UpdatePetRequest;

import java.util.Arrays;

public class PetDF {

    public static AddPetRequest createValidAddPetRequest() {
        return AddPetRequest.builder()
                .id(1L)
                .name("doggie")
                .photoUrls(Arrays.asList("http://example.com/photo1.jpg", "http://example.com/photo2.jpg"))
                .status("available")
                .category(Category.builder().id(1L).name("Dogs").build())
                .tags(Arrays.asList(
                        Tag.builder().id(1L).name("cute").build(),
                        Tag.builder().id(2L).name("friendly").build()
                ))
                .build();
    }

    public static AddPetRequest createAddPetRequestWithMinimalData() {
        return AddPetRequest.builder()
                .name("catty")
                .photoUrls(Arrays.asList("http://example.com/cat.jpg"))
                .status("available")
                .build();
    }

    public static UpdatePetRequest createValidUpdatePetRequest() {
        return UpdatePetRequest.builder()
                .id(1L)
                .name("doggie_updated")
                .photoUrls(Arrays.asList("http://example.com/photo1.jpg"))
                .status("pending")
                .category(Category.builder().id(1L).name("Dogs").build())
                .tags(Arrays.asList(
                        Tag.builder().id(1L).name("playful").build()
                ))
                .build();
    }

    public static AddPetRequest createAddPetRequestWithInvalidStatus() {
        return AddPetRequest.builder()
                .name("testpet")
                .status("invalid_status")
                .build();
    }

    public static AddPetRequest createAddPetRequestWithEmptyPayload() {
        return AddPetRequest.builder().build();
    }

    public static AddPetRequest createAddPetRequestWithNullName() {
        return AddPetRequest.builder()
                .name(null)
                .status("available")
                .build();
    }

    public static UpdatePetRequest createUpdatePetRequestWithInvalidId() {
        return UpdatePetRequest.builder()
                .id(999999999L)
                .name("nonexistent")
                .status("available")
                .build();
    }

    public static UpdatePetRequest createUpdatePetRequestWithEmptyPayload() {
        return UpdatePetRequest.builder().build();
    }

    public static Long getValidPetId() {
        return 1L;
    }

    public static Long getNonexistentPetId() {
        return 999999999L;
    }

    public static Long getInvalidPetId() {
        return -1L;
    }

    public static String getAvailableStatus() {
        return "available";
    }

    public static String getInvalidStatus() {
        return "invalid_status";
    }

    public static String getValidTag() {
        return "cute";
    }
}
