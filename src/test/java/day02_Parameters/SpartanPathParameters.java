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

        RestAssured.baseURI = "http://35.153.51.63:8000" ;
        RestAssured.basePath = "/api" ;

    }


    @DisplayName("testing /spartans/{id}")
    @Test
    public void testingSingleSpartan(){

        given().
                log().all().
                pathParam("id",104).
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
                get("/spartans/{id}" , 197).
        then().
                statusCode(is(200));


    }

    @DisplayName("testing /spartans/{id} body")
    @Test
    public void testingSingleSpartanBody(){

        given().
                log().all().
                pathParam("id", 197).
        when().
                get("/spartans/{id}").
        then().
                log().all().
                statusCode(equalTo(200)).
                //body("JSON PATH", is("VALUE"))
                body("id",is(197)).
                body("name", equalTo("Adam")).
                body(("gender"), is(equalTo("Male"))).
                body("phone",is(74615973451L)).
                body(containsString("Adam"));

    }



}

