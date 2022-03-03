package day03_GetRequest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Practice {

    //http://54.236.150.168:8000/api/spartans


    @BeforeAll //static method
    public static void init(){

        RestAssured.baseURI = "http://54.236.150.168";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }


    @DisplayName("simple test")
    @Test
    public void test1(){

        given().
                log().all().
                queryParam("gender","Female").
        when().
                get("/spartans/search").
        then().
                statusCode(is(200));


    }



}
