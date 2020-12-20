package day02_Parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest {


    @DisplayName("Get all Spartans")
    @Test
    public void getAllSpartanS(){

        //String url = "http://35.153.51.63:8000/api/spartans";

       //how to set base URL , port , base path so i can reuse them
        RestAssured.baseURI = "http://35.153.51.63:8000";
        RestAssured.basePath = "/api";

        //it will create the request URL as is
       // baseURI + basePath + what is after get("this part")

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

        // send the same request specifying the accept header is application/json
        // use baseuri basepath , check status code 200 , contentType header is json
        //http://35.153.51.63:8000
        given().
                baseUri("http://35.153.51.63:8000").//alternative way of doing it
                basePath("/api").
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



}
