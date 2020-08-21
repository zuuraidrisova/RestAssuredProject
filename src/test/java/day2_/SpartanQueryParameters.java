package day2_;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanQueryParameters {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63:8000" ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Testing /spartans/search Endpoint")
    @Test
    public void testingWithQueryParam(){

        given().
                log().all().
        when().
                get("spartans/search?gender=Male&namecontains=ea").
         then().
                statusCode(is(200)).
                log().all();


    }

    //making it more obvious
    @DisplayName("Testing /spartans/search Endpoint")
    @Test
    public void testingWithQueryParam2(){

        given().
                log().all().
                queryParam("gender", "Male").
                queryParam("nameContains", "ea").
        when().
                get("spartans/search").
        then().
                statusCode(is(200)).
                body("numberOfElements", is(3)).
                log().all();


    }



}
