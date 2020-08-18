package day01_Junit5Practice;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredMethodChaining {


    @DisplayName("first attempt for chaining")
    @Test
    public void chainingMethodsTest(){

        //given().
        //some more information you want to provide other than request URL
        //like header, query parameter, path variable, body...

        //when().
        //you send the request GET POST PUT PATCH, or DELETE

        //then()
        //Validate something here.
        //this is where assertions start, u can chain multiple assertions using . dot

        //http://54.174.216.245:8000/api/hello

        when().
                get("http://54.174.216.245:8000/api/hello"). //here we are sending request to url
        then().
                statusCode(200). //asserting status code is 200
                header("Content-Length","17");//asserting content length is 17


        System.out.println("Success");
    }

    @DisplayName("explaining asserting using hamcrest matcher ")
    @Test
    public void testingWithMatcher(){


        when().
                get("http://54.174.216.245:8000/api/hello"). //here we are sending request to url
                prettyPeek().
        then().
                statusCode(is(200)). //asserting status code is 200
                header("Content-Length", equalTo("17")).//asserting content length is 17
                header("Content-Type", containsString("text/plain")).//asserting if content type contains text
                body(is("Hello from Sparta"));//asserting whether body is Hello from Sparta

        System.out.println("Success");
    }

    @Test
    public void testing(){

        given().//add all ur request specific info like header, query parameter, path var, body here
                header("Accept", "application/xml").
        when().
                get("http://35.153.51.63:8000/api/spartans").
                prettyPeek().
         then().
                statusCode(200);

    }



}
