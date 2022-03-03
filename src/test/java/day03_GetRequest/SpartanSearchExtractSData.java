package day03_GetRequest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanSearchExtractSData {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }


    @DisplayName("extracting data from spartan api search endpoint")
    @Test
    public void testExtractingDataSearch(){


        Response response =

                given().
                        log().all(). //just to see if smth is wrong if my test fails
                        queryParam("gender", "Female").
                when().
                        log().all().
                        get("/spartans/search");
                        //.prettyPeek();


        JsonPath jsonPath = response.jsonPath();

       //get the value of totalElement field from response body

        int totalElement = jsonPath.getInt("totalElement");

        System.out.println("totalElement = " + totalElement);

        List<Integer> spartanIDs = jsonPath.getList("content.id");

        System.out.println("spartanIDs = " + spartanIDs);

        List<String> spartanNames = jsonPath.getList("content.name");

        System.out.println("spartanNames = " + spartanNames);


    }



}
