package day04_PostRequestWithDifferentBodyTypes;

import pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestWithPOJO {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI  = "http://35.153.51.63";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }


    //Serialization : pojo to json  and
    //De-Serialization : json to pojo

    @Test
    public void testPostRequestWithPojo(){


        Spartan spartan = new Spartan("Irina", "Female", 8237649146l);

       given().
               log().all().
               contentType(ContentType.JSON).
               body(spartan).
       when().
               post("/spartans").
       then().
               log().all().
               statusCode(is(201)).
               body("data.name", is(spartan.getName()));

    }


}
