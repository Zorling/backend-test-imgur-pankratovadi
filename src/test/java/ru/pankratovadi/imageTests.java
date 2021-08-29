package ru.pankratovadi;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
//В классе объявляем, что он наследует методы из BaseTest, что бы подтянулись все значения переменных
public class imageTests extends BaseTest{
    String imageDeleteHash;
    String imageDeleteHash1;


    @Test
    void uploadImageBase64Test(){
        imageDeleteHash =
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/wolfBig.jpeg"))
                .expect()
                .statusCode(200)
                .when()
                .post("image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash")

                //.then()
                //.statusCode(200)
        ;

    }
    @AfterEach
    void tearDown(){
        given()
                .header("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                .statusCode(200)
        ;
    }
// Домашняя работе 03
    //проверка загрузки большой картинки
    @Test
    void uploadBigImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации
                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .body(new File("src/test/resources/wolfBig.jpeg")) //Тело запроса, отправляем большую картинку
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200) // Проверка кода ответа
                        .body("data.width", CoreMatchers.equalTo(2249))
                        .body("data.height", CoreMatchers.equalTo(1500))
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") //отправка картинки
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод
        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownBigImage(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }
    //проверка загрузки средней картинки
    @Test
    void uploadMedImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации
                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .body(new File("src/test/resources/wolfMed.jpg")) //Тело запроса, отправляем среднюю картинку
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(1280))
                        .body("data.height", CoreMatchers.equalTo(800))

                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownMedImage(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //проверка загрузки маленькой картинки
    @Test
    void uploadSmallImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации
                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .body(new File("src/test/resources/wolfSmall.jpg")) //Тело запроса, отправляем маленькую картинку
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(550))
                        .body("data.height", CoreMatchers.equalTo(412))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownSmallImage(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //проверка загрузки PNG картинки
    @Test
    void uploadPNGImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации
                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .body(new File("src/test/resources/wolfPNG.png")) //Тело запроса, отправляем PNG картинку
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(1280))
                        .body("data.height", CoreMatchers.equalTo(1322))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownPNGImage(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //проверка загрузки Gif картинки
    @Test
    void uploadGifImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации
                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .body(new File("src/test/resources/5sLH.gif")) //Тело запроса, отправляем Gif картинку
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(500))
                        .body("data.height", CoreMatchers.equalTo(692))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownGifImage(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //Проверка загрузки картинки с описанием
    @Test
    void uploadImageDescription(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .contentType("multipart/form-data")
                        .multiPart("image", new File("src/test/resources/5sLH.gif"),"multipart/form-data")
                        .multiPart("description", "jjj","multipart/form-data")
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(500))
                        .body("data.height", CoreMatchers.equalTo(692))
                        .body("data.description", CoreMatchers.equalTo("jjj"))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

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
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //Проверка загрузки картинки с названием
    @Test
    void uploadImageName(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .contentType("multipart/form-data")
                        .multiPart("image", new File("src/test/resources/5sLH.gif"),"multipart/form-data")
                        .multiPart("name", "nnn","multipart/form-data")
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(500))
                        .body("data.height", CoreMatchers.equalTo(692))
                        .body("data.name", CoreMatchers.equalTo("nnn"))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.deletehash") //получаем хеш картинки для передачи на удаление в следующий метод

        ;

    }
    //Вызывается после выполнения нашего теста, что бы подчистить тестовые данные за собой
    @AfterEach
    void tearDownImageName(){
        given()
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //Проверка загрузки картинки с заголовком
    @Test
    void uploadImageTitle(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .contentType("multipart/form-data")
                        .multiPart("image", new File("src/test/resources/5sLH.gif"),"multipart/form-data")
                        .multiPart("title", "iii","multipart/form-data")
                        //Все боди выносить сюда


                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(500))
                        .body("data.height", CoreMatchers.equalTo(692))
                        .body("data.title", CoreMatchers.equalTo("iii"))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

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
                .header("Authorization", token)
                .when()
                //Вызываем запрос на удаление картинки используя полученный в предыдущем запросе imageHash
                .delete("https://api.imgur.com/3/image/{imageHash}", imageDeleteHash)
                .then()
                //Проверяем, что ответ успешный
                .statusCode(200)
        ;
    }

    //Проверка существования GIF картинки
    @BeforeEach
    void postGIFImage(){
        imageDeleteHash =
                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .contentType("multipart/form-data")
                        .multiPart("image", new File("src/test/resources/5sLH.gif"),"multipart/form-data")
                       // .multiPart("title", "iii","multipart/form-data")
                        //Все боди выносить сюда

                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.id") //получаем хеш картинки для передачи на удаление в следующий метод

        ;}


           @Test
    void getGIFImage(){

                given()
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда




                        //Все проверки ответа выносить сюда
                        .expect()//Проверка
                        .statusCode(200)
                        .body("data.width", CoreMatchers.equalTo(500))
                        .body("data.height", CoreMatchers.equalTo(692))
                        //.body("data.title", CoreMatchers.equalTo("iii"))


                        // Проверка кода ответа
                        //Все проверки ответа выносить сюда

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
                        //Весь хедер выносить сюда
                        .header("Authorization", token) // Параметр авторизации

                        //Весь хедер выносить сюда

                        //Все боди выносить сюда
                        .contentType("multipart/form-data")
                        .multiPart("image", new File("src/test/resources/wolfBig.jpeg"),"multipart/form-data")
                        // .multiPart("title", "iii","multipart/form-data")
                        //Все боди выносить сюда

                        //Все проверки ответа выносить сюда

                        .when() //Отправка нашего запроса
                        .post("image") // URL куда отправлять картинку
                        .prettyPeek() // задаем вывод читаемого ответа
                        .jsonPath() //подключаем метод чтения
                        .get("data.id") //получаем хеш картинки для передачи на удаление в следующий метод

        ;}


    @Test
    void getBigImage(){

        given()
                //Весь хедер выносить сюда
                .header("Authorization", token) // Параметр авторизации

                //Весь хедер выносить сюда




                //Все проверки ответа выносить сюда
                .expect()//Проверка
                .statusCode(200)
                .body("data.width", CoreMatchers.equalTo(2249))
                .body("data.height", CoreMatchers.equalTo(1500))
                //.body("data.title", CoreMatchers.equalTo("iii"))


                // Проверка кода ответа
                //Все проверки ответа выносить сюда

                .when() //Отправка нашего запроса
                .get("image/{imageHash}", imageDeleteHash1) // URL куда отправлять картинку
                .prettyPeek() // задаем вывод читаемого ответа

        ;

    }



}
