package ru.pankratovadi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
//В классе объявляем, что он наследует методы из BaseTest, что бы подтянулись все значения переменных
public class imageTests extends BaseTest{
    static String imageDeleteHash;
    String imageDeleteHash2;
    String imageDeleteHash1;

    static RequestSpecification requestSpec = given()
            .baseUri(host)
            .header("Authorization", token);
    RequestSpecification requestSpecMulti = given()
            .baseUri(host)
            .header("Authorization", token)
            .contentType("multipart/form-data");

    static ResponseSpecification responseSpec = given()

            .expect()
            .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON)

            ;
    ResponseSpecification responseSpecBig = given()
            .expect()
            .body("data.width", CoreMatchers.equalTo(bigwidth))
            .body("data.height", CoreMatchers.equalTo(bigheight))
            ;
    ResponseSpecification responseSpecMed = given()
            .expect()
            .body("data.width", CoreMatchers.equalTo(medwidth))
            .body("data.height", CoreMatchers.equalTo(medheight))
            ;
    ResponseSpecification responseSpecSmall = given()
            .expect()
            .body("data.width", CoreMatchers.equalTo(smallwidth))
            .body("data.height", CoreMatchers.equalTo(smallheight))
            ;
    ResponseSpecification responseSpecPNG = given()
            .expect()
            .body("data.width", CoreMatchers.equalTo(PNGwidth))
            .body("data.height", CoreMatchers.equalTo(PNGheight))
            ;
    ResponseSpecification responseSpecGIF = given()
            .expect()
            .body("data.width", CoreMatchers.equalTo(GIFwidth))
            .body("data.height", CoreMatchers.equalTo(GIFheight))
            ;


    @Test
    void uploadImageBase64Test(){
        imageDeleteHash =
        given()
                .spec(requestSpec)
                .body(new File(urlbig))
                .expect()
                .spec(responseSpec)
                //.spec(responseSpec2)
                .when()
                .post("image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash")
        ;
    }

// Домашняя работе 03
    //проверка загрузки большой картинки
    @Test
    void uploadBigImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpec)

                        .body(new File(urlbig)) //Тело запроса, отправляем большую картинку

                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecBig)

                        .when() //Отправка нашего запроса
                        .post("image") //отправка картинки
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод
        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
   /* @AfterEach
    void tearDownBigImage(){
        given()
                .spec(requestSpec)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }*/
    //проверка загрузки средней картинки
    @Test
    void uploadMedImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpec)
                        .body(new File(urlmed)) //Тело запроса, отправляем среднюю картинку
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecMed)
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    @AfterEach
    void tearDownMedImage(){
        given()
                .spec(requestSpec)
                .when()
                .delete(urlmed, imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }
    //проверка загрузки маленькой картинки
    @Test
    void uploadSmallImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpec)
                        //Все боди выносить сюда
                        .body(new File(urlsmall)) //Тело запроса, отправляем маленькую картинку
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecSmall)
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    @AfterEach
    void tearDownSmallImage(){
        given()
                .spec(requestSpec)
                .when()
                .delete(urlsmall, imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }
    //проверка загрузки PNG картинки
    @Test
    void uploadPNGImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpec)
                        .body(new File(urlPNG)) //Тело запроса, отправляем PNG картинку
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecPNG)
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    @AfterEach
    void tearDownPNGImage(){
        given()
                .spec(requestSpec)
                .when()
                .delete(urlPNG, imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }

    //проверка загрузки Gif картинки
    @Test
    void uploadGifImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpec)
                        .body(new File(urlGIF)) //Тело запроса, отправляем Gif картинку
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecGIF)
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    @AfterEach
    void tearDownGifImage(){
        given()
                .spec(requestSpec)
                .when()
                .delete(urlGIF, imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }

    //Проверка загрузки картинки с описанием
    @Test
    void uploadImageDescription(){
        imageDeleteHash =
                given()
                        .spec(requestSpecMulti)
                        .multiPart("image", new File(urlGIF),"multipart/form-data")
                        .multiPart("description",text,"multipart/form-data")
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecGIF)
                        .body("data.description", CoreMatchers.equalTo(text))
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }

    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownImageDescription(){
        given()
                .spec(requestSpec)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }
    //Проверка загрузки картинки с названием
    @Test
    void uploadImageName(){
        imageDeleteHash =
                given()
                        .spec(requestSpecMulti)
                        .multiPart("image", new File(urlGIF),"multipart/form-data")
                        .multiPart("name", text,"multipart/form-data")
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecGIF)
                        .body("data.name", CoreMatchers.equalTo(text))
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    @AfterEach
    void tearDownImageName(){
        given()
                .spec(requestSpec)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }

    //Проверка загрузки картинки с заголовком
    @Test
    void uploadImageTitle(){
        imageDeleteHash =
                given()
                        .spec(requestSpecMulti)
                        .multiPart("image", new File(urlGIF),"multipart/form-data")
                        .multiPart("title", text,"multipart/form-data")
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecGIF)
                        .body("data.title", CoreMatchers.equalTo(text))
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод
        ;
    }

    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownImageTitle(){
        given()
                .spec(requestSpec)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                .spec(responseSpec)
        ;
    }
    //Проверка существования GIF картинки
    @BeforeEach
    void postGIFImage(){
        imageDeleteHash =
                given()
                        .spec(requestSpecMulti)
                        .multiPart("image", new File(urlGIF),"multipart/form-data")
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.id") //получаем хеш картинки для передачи на удаление в следующий метод

        ;}


           @Test
    void getGIFImage(){

                given()
                        .spec(requestSpec)
                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .spec(responseSpec)
                        .spec(responseSpecGIF)
                        .when() //Отправка нашего запроса
                        .get("image/{imageHash}", imageDeleteHash) // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
        ;
    }
    //Проверка существования большой картинки
    @BeforeEach
    void postBigImage(){
        imageDeleteHash1 =
                given()
                        .spec(requestSpecMulti)
                        .multiPart("image", new File(urlbig),"multipart/form-data")
                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.id") //получаем хеш картинки для передачи на удаление в следующий метод

        ;}
    @Test
    void getBigImage(){
        given()
                .spec(requestSpec)
                .expect()//Проверка
                .spec(responseSpec)
                .spec(responseSpecBig)
                .when() //Отправка нашего запроса
                .get("image/{imageHash}", imageDeleteHash1) // URL куда отправлять картинку
                .prettyPeek() // задаем вывод читаемого ответа
        ;
    }
    @AfterAll
    static void tearDown(){
        given()
                .spec(requestSpec)
                .expect()
                .spec(responseSpec)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
        ;
    }
}
