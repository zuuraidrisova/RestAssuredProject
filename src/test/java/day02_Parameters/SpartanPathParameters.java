package day02_Parameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPathParameters {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.236.150.168:8000" ;
        RestAssured.basePath = "/api" ;

    }


    @DisplayName("testing /spartans/{id}")
    @Test
    public void testingSingleSpartan(){

        given().
                log().all().
                pathParam("id",979).
        when().
                get("/spartans/{id}").
                prettyPeek().
        then().
                statusCode(is(200));

    }


    @DisplayName("testing /spartans/{id}")
    @Test
    public void testingSingleSpartan2(){

        given().
                log().all().
        when().
                get("/spartans/{id}" , 979).
        then().
                statusCode(is(200));


    }


    @DisplayName("testing /spartans/{id} body")
    @Test
    public void testingSingleSpartanBody(){

        given().
                log().all().
                pathParam("id", 979).
        when().
                get("/spartans/{id}").
        then().
                log().all().
                statusCode(equalTo(200)).
                //body("JSON PATH", is("VALUE"))
                body("id",is(979)).
                body("name", equalTo("Elsie")).
                body(("gender"), is(equalTo("Male"))).
                body("phone",is(1604036455)).
                body(containsString("Elsie"));

    }



}

