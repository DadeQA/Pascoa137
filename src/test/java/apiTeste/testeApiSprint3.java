package apiTeste;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class testeApiSprint3 {
    public static String token;
    static String ct = "application/json";
    static String uriUser = "https://restful-booker.herokuapp.com/auth/";
    static String bookUser = "https://restful-booker.herokuapp.com/booking/";


    public static String lerArquivojson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    public void testarCriarToken() throws IOException {

        String Username = "admin";
        String password = "password123";
        String body = lerArquivojson("src/test/resources/json/user5.json");
        Response resp = (Response)
                given()
                        .contentType(ct)
                        .log().all()
                        .body(body)
                        .when()
                        .post(uriUser)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract();
        token = resp.jsonPath().getString("token");
        System.out.println("token: " + token);

    }

    @Test

    public void testarIncluirUser() throws IOException {
        String jsonBody = lerArquivojson("src/test/resources/json/user10.json");
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(bookUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", is("Jose"))
                .body("booking.lastname", is("Artigas"))
                .body("booking.totalprice", is(100))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", is("2023-01-01"))
                .body("booking.bookingdates.checkout", is("2023-02-01"))
                .body("booking.additionalneeds", is("Breakfast"))

        ;
    }// fim do post

    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaUser1.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarCriarTokenCSV(
            String username,
            String password){
        User user = new User();
        user.username = username;
        user.password = password;
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);
            Response resp = (Response)
                given()
                        .contentType(ct)
                        .log().all()
                        .body(jsonBody)
                .when()
                        .post(uriUser)
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract();
        token = resp.jsonPath().getString("token");
        System.out.println("token: " + token);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaUser2.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirUserCSV(
            String firstname,
            String lastname,
            String totalprice,
            String depositpaid,
            String chekin,
            String checkout,
            String additionalneeds
            ){
        User user = new User();
        user.firstname = firstname;
        user.lastname = lastname;
        user.totalprice = totalprice;
        user.depositpaid = depositpaid;
        user.checkin = chekin;
        user.checkout = checkout;
        user.additionalneeds = additionalneeds;
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);
                given()
                        .contentType(ct)
                        .log().all()
                        .body(jsonBody)
                        .when()
                        .post(bookUser)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("booking.firstname", is("Jose"))
                        .body("booking.lastname", is("Artigas"))
                        .body("booking.totalprice", is(100))
                        .body("booking.depositpaid", is(true))
                        .body("booking.bookingdates.checkin", is("2023-01-01"))
                        .body("booking.bookingdates.checkout", is("2023-02-01"))
                        .body("booking.additionalneeds", is("Breakfast"))


                ;
    }

    }








