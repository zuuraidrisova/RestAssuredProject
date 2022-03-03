package day03_GetRequest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRequestExample {

    @BeforeAll
    public static  void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }


    @Test
    public void testDelete(){

        when().
                delete("/spartans/{id}", 931).
        then().
                statusCode(is(204));

    }

}
