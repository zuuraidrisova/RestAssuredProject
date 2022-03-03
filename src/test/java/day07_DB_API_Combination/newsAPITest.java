package day07_DB_API_Combination;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.*;


public class newsAPITest {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://newsapi.org";
        RestAssured.basePath = "/v2";

    }

    // query parameter always goes with  ? and it is used to filter the result
    //path parameter always followed by : and it is used to get a single source among all response


    @DisplayName("News api get all author if the source is not null")
    @Test
    public void testNews(){

        String apiKey = "47995edfa3ee4079b8b677d9619aff84";

        Response  response =

        //?country=us
        given().

                header("Authorization", "Bearer "+apiKey).
                queryParam("country", "us").
                log().all().

        get("/top-headlines");

        JsonPath jsonPath = response.jsonPath();

        List<String> allAuthors =  jsonPath.getList("articles.author");

        System.out.println("allAuthors = " + allAuthors);

        //lambda expression
        allAuthors.forEach( eachAuthor -> System.out.println("eachAuthor = " + eachAuthor));

        System.out.println("=======================================================");

        List<String> allAuthorsFiltered = jsonPath.getList("articles.findAll{it.source.id != null }.author");

        for(String each : allAuthorsFiltered){

            System.out.println("each = " + each);
        }


        int count =  allAuthors.size();

        int count2 = allAuthorsFiltered.size();

        System.out.println("count = " +count +", count2 = "+count2);

        Assertions.assertTrue(allAuthors.size() != allAuthorsFiltered.size());



    }




}
