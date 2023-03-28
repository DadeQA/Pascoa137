// nome de pacote
package apiTeste;

//biblioteca


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

//Classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TesteUser {
    //Atributos
    static String ct = "application/json";  // content tytpe
    static String uriUser = "https://petstore.swagger.io/v2/user/";
    static String uriPet= "petstore.swagger.io/v2/pet/";
    //Funções e Métodos
    //Funções de apoio

    public static String lerArquivojson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test @Order(1)

   public void testarIncluirUser() throws IOException {
// carregar os dados de nosso Json
        String jsonBody = lerArquivojson("src/test/resources/json/user1.json");

        String userId = "1372445035";

// Realizar o teste
        given()                                            // Dado que
                .contentType(ct)        // o tipo conteudo
                .log().all()                               // mostre tudo
                .body(jsonBody)                            // corpo da requisição
        .when()                                            // Quando
                .post(uriUser)                       //endPoint
        .then()                                            // Então
                .log().all()                               //mostre tudo na volta
                .statusCode(200)                        // Comunic. ida e volta ok
                .body("code",is(200))              // tag code 200
                .body("type", is("unknown"))
                .body("message", is(userId))             // message é o userId
        ;
    }// fim do post
    @Test @Order(2)

    public void testarConsultarUser(){
        String username = "Dedphm";

        // resultados esperados
        int userId = 1372445035;    // código de usuário
        String email = "kuku@teste.com";
        String senha = "13456";
        String telefone = "11999996678";
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is(email))
                .body("password", is(senha))
                .body("phone", is (telefone))
        ;

    }//fim do get user

    @Test  @Order(3)

    public void testarAlterarUser() throws IOException {
        String jsonbody = lerArquivojson("src/test/resources/json/user2.json");
        String userId = "1372445035";
        String username = "Dedphm";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonbody)
        .when()
                .put(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;



    } // fim put user

    @Test  @Order(4)

    public void testarExcluirUser(){
        String username = "Dedphm";

        given()
                .contentType(ct)
                .log() .all()
        .when()
                .delete(uriUser + username)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username))
        ;

    }
    @Test  @Order(5)
    public void testarLogin(){
        String Username = "Dedphm";
        String password = "13456";
        Response response = (Response) given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "login?username=" + Username +"&password=" + password )
        .then()
                .log().all()
                .statusCode(200)
                .body("code",is (200))
                .body("type", is ("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
        .extract()
        ;

        //Extração do token da resposta
        String token = response.jsonPath().getString("message").substring(23);
                System.out.println("Conteudo do token"  +  token);
    }

    @ParameterizedTest  @Order(6)
    @CsvFileSource(resources = "csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirUserCsv(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) {


        // Inicio incluir CSV
        /*

            StringBuilder jsonBody = new StringBuilder("{");
            jsonBody.append("'id': " + id + ",");
            jsonBody.append("'username': ").append(username).append(",");
            jsonBody.append("'firstName': " + firstName + ",");
            jsonBody.append("'lastName': " + lastName + ",");
            jsonBody.append("'email': " + email + ",");
            jsonBody.append("'password': " + password + ",");
            jsonBody.append("'phone': " + phone + ",");
            jsonBody.append("'userStatus': " + userStatus);
            jsonBody.append("}");

         */
        User user = new User();

        user.id = id;
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.phone = phone;
        user.userStatus = userStatus;

        Gson gson = new Gson(); // instancia a classe Gson
        String jsonBody = gson.toJson(user);



// Realizar o teste
        given()                                            // Dado que
                .contentType(ct)        // o tipo conteudo
                .log().all()                               // mostre tudo
                .body(jsonBody)                            // corpo da requisição
                .when()                                            // Quando
                .post(uriUser)                       //endPoint
                .then()                                            // Então
                .log().all()                               //mostre tudo na volta
                .statusCode(200)                        // Comunic. ida e volta ok
                .body("code", is(200))              // tag code 200
                .body("type", is("unknown"))
                .body("message", is(id))             // message é o userId
        ;
    }


// Inicio post (pet)
    @Test
    public void testarIncluirPet() throws IOException {

        String jsonBody =  lerArquivojson("src/test/resources/json/user3.json");
        String userId = "12021981";
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;
    }
    // get pet
    @Test
    public void testarConsultarPet(){

        String petId = "Sergio";
        int userid = 12021981;
        String name ="Gato";
        String status = "available";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriPet + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id",is(petId))
                .body("name", is(name))
                .body("status", is(status))
        ;

    }// post store

    @Test
    public void testarIncluirStoreOrder() throws IOException {
        String jsonbody = lerArquivojson("src/test/resources/json/user4.json");
        String userId = "12021981";
        given()
                .contentType(ct)
                .log().all()
                .body(jsonbody)
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;






    }


}


