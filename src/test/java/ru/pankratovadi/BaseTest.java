package ru.pankratovadi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
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
    static String urlbig;
    static String urlmed;
    static String urlsmall;
    static String urlPNG;
    static String urlGIF;
    static String text;
    static int bigwidth;
    static int bigheight;
    static int medwidth;
    static int medheight;
    static int smallwidth;
    static int smallheight;
    static int PNGwidth;
    static int PNGheight;
    static int GIFwidth;
    static int GIFheight;

    public  static ResponseSpecification responseSpecification = null;
   // static RequestSpecification requestSpecification;
    @BeforeAll
    static void beforeAll() throws IOException {

       /* responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200) // Проверка кода ответа
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build(); */


        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        host = properties.getProperty("host");
        username = properties.getProperty("username");
        token = properties.getProperty("auth.token");
        imgname = properties.getProperty("imgname");
        urlbig = properties.getProperty("urlBig");
        urlmed = properties.getProperty("urlMed");
        urlsmall = properties.getProperty("urlSmall");
        urlPNG = properties.getProperty("urlPNG");
        urlGIF = properties.getProperty("urlGIF");
        text = properties.getProperty("text");
        bigwidth = Integer.parseInt(properties.getProperty("BigWidth"));
        bigheight = Integer.parseInt(properties.getProperty("BigHeight"));
        medwidth = Integer.parseInt(properties.getProperty("MedWidth"));
        medheight = Integer.parseInt(properties.getProperty("MedHeight"));
        smallwidth = Integer.parseInt(properties.getProperty("SmallWidth"));
        smallheight = Integer.parseInt(properties.getProperty("SmallHeight"));
        PNGwidth = Integer.parseInt(properties.getProperty("PNGWidth"));
        PNGheight = Integer.parseInt(properties.getProperty("PNGHeight"));
        GIFwidth = Integer.parseInt(properties.getProperty("GIFWidth"));
        GIFheight = Integer.parseInt(properties.getProperty("GIFHeight"));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host;

        RestAssured.responseSpecification = responseSpecification;



    }

}
