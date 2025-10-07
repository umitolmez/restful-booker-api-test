package api;

import config.ConfigReader;
import models.AuthRequest;
import models.AuthResponse;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthService {

    private static final String AUTH_ENDPOINT = "/auth";

    public String getAuthToken() {
        AuthRequest authRequest = AuthRequest.builder()
                .username(ConfigReader.getProperty("username"))
                .password(ConfigReader.getProperty("password"))
                .build();

        return given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post(ConfigReader.getBaseUrl() + AUTH_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class)
                .getToken();
    }
}
