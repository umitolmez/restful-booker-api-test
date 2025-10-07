package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }
}