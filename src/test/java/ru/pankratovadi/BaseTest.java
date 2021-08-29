package ru.pankratovadi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String username;
    static String token;
    static String imgname;

    @BeforeAll
    static void beforeAll() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        host = properties.getProperty("host");
        username = properties.getProperty("username");
        token = properties.getProperty("auth.token");
        imgname = properties.getProperty("imgname");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host;
    }
}
