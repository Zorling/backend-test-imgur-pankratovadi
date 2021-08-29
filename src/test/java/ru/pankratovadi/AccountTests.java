
package ru.pankratovadi;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class AccountTests extends BaseTest{


    @Test
    void getAccountPositiveTest(){
        given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get(  "account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.url", CoreMatchers.equalTo(username));


    }


}
