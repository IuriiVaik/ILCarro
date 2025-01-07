package okhttp;

import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

public class LoginTestsOkHttp implements BaseApi {

    @Test
    public void loginPositiveTest() {

        UserDtoLombok user = UserDtoLombok.builder()
                .username("test_user@mail.com")
                .password("Pass123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Response is successful: " + response.isSuccessful());
        System.out.println("Response code: " + response.code());
        try {
            System.out.println("Response body: " + response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void loginNegativeInvalidPasswordTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("test_user@mail.com")
                .password("InvalidPass123")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void loginNegativeEmptyFieldsTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("")
                .password("")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void loginNegativeUnknownUserTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("unknown_user@mail.com")
                .password("Pass123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 401);
    }
}
