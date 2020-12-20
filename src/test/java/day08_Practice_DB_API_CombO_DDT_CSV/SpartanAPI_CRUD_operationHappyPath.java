package day08_Practice_DB_API_CombO_DDT_CSV;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_CRUD_operationHappyPath {


    public static int newID;

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }



    @Order(1)
    @DisplayName("posting with map object as body")
    @Test
    public void testCreateData(){

        Map<String, Object> bodyMap = new LinkedHashMap<>();

        bodyMap.put("name", "Sary");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 27918356491l);

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
                body(bodyMap).
        when().
                post("/spartans");


        newID = response.jsonPath().getInt("data.id");

        System.out.println("newID = " + newID);

        assertThat(response.getStatusCode(), is(201) );

        //201 status code is successfully created a data
    }



    @Order(2)
    @DisplayName("retrieving data we just created using newID static variable")
    @Test
    public void testReadData(){

        given().
                log().all().
                contentType(ContentType.JSON).
                pathParam("id", newID).
        when().
                get("/spartans/{id}").
        then().
                log().all().
                statusCode(is(200)).
                body("name", equalTo("Sary")).
                body("gender", equalTo("Male"));

        //200 status code is success msg

    }

    @Order(3)
    @DisplayName("updating data with pojo object as body")
    @Test
    public void testUpdateData(){

        Faker faker = new Faker();

        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000l, 9999999999l);

        Spartan spartan = new Spartan(name, gender, phone);

        given().
                log().all().
                pathParam("id", newID).
                contentType(ContentType.JSON).
                body(spartan).
        when().
                put("/spartans/{id}").
        then().
                statusCode(is(204));

        //204 status code is successfully completed request without content

    }

    @Order(4)
    @DisplayName("deleting the data we generated")
    @Test
    public void testDeleteData(){

        given().
                log().all().
                pathParam("id", newID).
        when().
                delete("/spartans/{id}").
        then().
                log().all().
                statusCode(equalTo(204));

        //204 status code is successfully completed request without content
    }



}
