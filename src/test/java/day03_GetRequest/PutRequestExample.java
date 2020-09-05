package day03_GetRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PutRequestExample {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }


    @DisplayName("updating the existing data")
    @Test
    public void testUpdateSpartan(){

        //282


        String myBodyData = " {\n" +
                "            \"name\": \"Asko\",\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"phone\": 2189674942\n" +
                "        }";


      given().
            log().all().
            contentType(ContentType.JSON).
            body(myBodyData).
      when().
              put("/spartans/{id}", 282).
      then().
              log().all().
              statusCode(is(204));



    }










}
