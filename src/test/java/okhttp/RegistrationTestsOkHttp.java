package okhttp;

import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestsOkHttp implements BaseApi {

    @Test
    public void registrationPositiveTest(){
        int i  = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("Alster")
                .lastName("Mudi")
                .username(i+"alaster_mail@mail.com")
                .password("Pass123!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION)
                .post(requestBody)
                .build();
        Response response;
        try {
           response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        System.out.println(response.code());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 200);
    }



    @Test
    public void registrationNegativeEmptyFieldsTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("")
                .lastName("")
                .username("")
                .password("")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegativeInvalidEmailTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("Hermiona")
                .lastName("Grendjer")
                .username("invalid-email-format")
                .password("Pass123!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegativeDuplicateUsernameTest() {
        String duplicateUsername = "test_user@mail.com";

        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("Harry")
                .lastName("Potter")
                .username(duplicateUsername)
                .password("Pass123!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();


        try {
            Response initialResponse = OK_HTTP_CLIENT.newCall(request).execute();
            Assert.assertEquals(initialResponse.code(), 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            Response duplicateResponse = OK_HTTP_CLIENT.newCall(request).execute();
            Assert.assertEquals(duplicateResponse.code(), 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registrationNegativeShortPasswordTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("Ron")
                .lastName("Pon")
                .username("ron_mail@mail.com")
                .password("123")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400 );
    }


}
