package day03_GetRequest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class ExtractingDataUsingJsonPathMethodsMovieAPI {


    @DisplayName("getting movie data")
    @Test
    public void test(){

        Response response =

                given().
                        log().all().
                       // baseUri("http://www.omdbapi.com").
                        queryParam("apikey","26aa5b74").
                        queryParam("t", "Boss Baby").
                when().
                        get("http://www.omdbapi.com");

            //jsonPath takes no parameters and returns an object called JsonPath

        //JsonPath is a class  coming from restAssured
        // that has lots of methods to get the body data
        // in different data types
        //we get this object by calling the method of Response object called .jsonPath();

        JsonPath jp = response.jsonPath();

        //get title as string
        String title = jp.getString("Title");
        System.out.println("title = " + title);


        //get year as int
        int year = jp.getInt("Year");
        System.out.println("year = " + year);


        //get director as string
        String director = jp.getString("Director");
        System.out.println("director = " + director);


        //get the first rating source as string
        String ratingSRC = jp.getString("Ratings[0].Source");
        System.out.println("ratingSRC = " + ratingSRC);


        //store the entire response as map :  Map< String, Object >
        //since our response is a json object  with key value pair
        //we can directly call getMap method and provide the path, store the whole thing in map object
        Map<String, Object> responseMap = jp.getMap("");

        System.out.println("responseMap = " + responseMap);

        System.out.println("responseMap.size() = " + responseMap.size());

        System.out.println("Awards:  " + responseMap.get("Awards"));


        //store first rating json object into a map

        Map<String , Object> firstRatingMap = jp.getMap("Ratings[0]");

        System.out.println("firstRatingMap = " + firstRatingMap);



    }


}
