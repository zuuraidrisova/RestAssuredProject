package day2_;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest2 {

    @BeforeAll
    public static void setUp(){

        //http://35.153.51.63:8000
        //how to set base URL , port , base path so i can reuse them
        RestAssured.baseURI = "http://35.153.51.63:8000";
        RestAssured.basePath = "/api";

        //it will create the request URL as is
        // baseURI + basePath + what is after get("this part")

    }

    @DisplayName("Get 1 single spartan")
    @Test
    public void testingSingleSpartan(){

        //i want to log the request i sent so i see what url is, methods and etc
        given().
                log().uri().
        when().
                get("/spartans/197").
              //  prettyPeek().
        then().
                log().ifValidationFails().
                statusCode(is(200));


    }

    @DisplayName("Get all Spartans")
    @Test
    public void getAllSpartanS(){

        //String url = "http://35.153.51.63:8000/api/spartans";

        given().
                header("Accept", "application/json;charset=UTF-8").
         when().
                get("/spartans").
                prettyPeek().
        then().
                statusCode(is(200));


    }



    @DisplayName("Get all Spartans 2")
    @Test
    public void testAllSpartans(){

        // send the same request specifiying the accept header is application/json
        // use baseuri basepath , check status code 200 , contentType header is json

        given().
                //accept("application/json").
                accept(ContentType.JSON).
         when().
                get("/spartans").
         then().
                statusCode(is(200)).
                // contentType(is("application/json;charset=UTF-8")).
                        contentType(ContentType.JSON);
        //contentType("application/json;charset=UTF-8")

    }

    //add another test for hello endpoint by reusing the baseURI , basePath above
    //specify that you want to get a text result back
    //check status code is 200
    //check content type is text
    //check the body is Hello from Sparta
    @DisplayName("testing hello again")
    @Test
    public void testingHello(){

        given().
                accept(ContentType.TEXT). //specifying that you want to get a text result back
        when().
                get("/hello"). //sending request to aseuri basepath + /hello
                prettyPeek().
        then().
                statusCode(is(200)).//checking sttaus code is 200
                contentType(ContentType.TEXT). //checking content type is text
                body(equalTo("Hello from Sparta")); //checking the body

    }





}
