package utils;

public class ApiEndPoints {
    public static final String BaseUrl = "https://petstore.swagger.io/v2";
    
    // User endpoints
    public static final String USER_BASE = "/user";
    public static final String CREATE_USER = "/user";
    public static final String CREATE_USER_WITH_ARRAY = "/user/createWithArray";
    public static final String CREATE_USER_WITH_LIST = "/user/createWithList";
    public static final String GET_USER = "/user/{username}";
    public static final String UPDATE_USER = "/user/{username}";
    public static final String DELETE_USER = "/user/{username}";
    public static final String LOGIN_USER = "/user/login";
    public static final String LOGOUT_USER = "/user/logout";
    
    // Pet endpoints
    public static final String PET_BASE = "/pet";
    public static final String ADD_PET = "/pet";
    public static final String UPDATE_PET = "/pet";
    public static final String GET_PET = "/pet/{petId}";
    public static final String DELETE_PET = "/pet/{petId}";
    public static final String FIND_PETS_BY_STATUS = "/pet/findByStatus";
    public static final String FIND_PETS_BY_TAGS = "/pet/findByTags";
    public static final String UPDATE_PET_FORM = "/pet/{petId}";
    public static final String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    
    // Store endpoints
    public static final String PLACE_ORDER = "/store/order";
    public static final String GET_ORDER = "/store/order/{orderId}";
    public static final String GET_INVENTORY = "/store/inventory";
}
