package day11_HowToHandleDynamicData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.Spartan2;
import utility.ConfigurationReader;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
***If you do not have access to DB but have access to API
			Send the request to the endpoint that return your data and get
			*  the fields that you want using jsonPath.
 */

public class GettingDataFromOtherRequests {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }
/**
 * We want to test GET /Spartans/{id}
 * and the ID is dynamic , and also different in different environments
 * we want to always work with available data
 * without dealing with 404 issue because data does not exists
 *
 *  Action Items
 *      *  1. Send a GET /spartans endpoint
 *      *  2. store the result as List of pojo
 *      *  3. initially just the the first data and use it for GET /Spartans/{id} request
 *      *      and use the name , gender , phone for body validation
 *      *  4 , eventually randomize the way you get the ID from List of Pojo
 */

    @DisplayName("test GET /spartans/{id}")
    @Test
    public void testGetDataFromOtherRequests(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
        when().
                get("/spartans");


        List<Spartan2> listOfSpartans = response.jsonPath().getList("", Spartan2.class);

        System.out.println("listOfSpartans = " + listOfSpartans);

        //Get/spartans/{id}

        // get the first spartan id so we can send below request :
        // we are calling list method get(0) to get first Spartan2 Object
        // then we called getter method getId() from Spartan2 to get the value

       int firstSpartanIDFromList =  listOfSpartans.get(0).getId();
       String firstSpartanNameFromList  = listOfSpartans.get(0).getName();

        System.out.println("firstSpartanIDFromList = " + firstSpartanIDFromList);


        given().
                pathParam("id", firstSpartanIDFromList).
        when().
                get("/spartans/{id}").
        then().
                statusCode(is(200)).
                body("name", is(firstSpartanNameFromList));


    }


    //can i repeat certain test n number of times in Junit5
    //use @RepeatedTest instead of @Test
    @RepeatedTest(3)
    public void gettingRandomIdAndNameForEachTest(){


        Response response =

                get("/spartans");


        List<Spartan2> spartan2List = response.jsonPath().getList("", Spartan2.class);

        // get random spartan object from the list each time
        // our range for the index will be 0--->spartan2List.size()

        Random random = new Random();

        int randomIndex = random.nextInt(spartan2List.size());

        System.out.println("randomIndex = " + randomIndex);

        Spartan2 randomSpartanObject =  spartan2List.get(randomIndex);

        System.out.println("randomSpartanObject = " + randomSpartanObject);

        given().
                pathParam("id",randomSpartanObject.getId()).
         when().
                get("/spartans/{id}").
         then().
                statusCode(is(200)).
                body("name", is(randomSpartanObject.getName()));

    }



    @AfterAll
    public static void tearDown(){

        RestAssured.reset();
    }






}
