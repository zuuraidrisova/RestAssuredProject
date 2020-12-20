package day03_GetRequest;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ExtractingDataFromResponseBody {

    @DisplayName("getting movie info")
    @Test
    public void test(){

        // provide your baseURI in the test
        // add query parameters
        // apikey 6acf8091 here
        // t = Boss Baby
        // Save the response into response object


        Response response =
            given().
                    log().all().
                    baseUri("http://www.omdbapi.com").
                    queryParam("apikey","6acf8091").
                    queryParam("t","Boss Baby").
            when().
                    get();


        response.prettyPrint();


        int actualStatusCode = response.statusCode();

        System.out.println("statusCode = " + actualStatusCode);

        int expectedStatusCode = 200;

        Assertions.assertTrue(expectedStatusCode == actualStatusCode);


        String actualTitle = response.path("Title");

        System.out.println("title = " +  actualTitle);

        String expectedTitle = "The Boss Baby";

        Assertions.assertEquals(actualTitle,expectedTitle);



        String year = response.path("Year");

        String expectedYear = "2017";

        System.out.println("year = " + year);

        Assertions.assertEquals(expectedYear, year);




        String director = response.path("Director");

        String expectedDirector = "Tom McGrath";

        System.out.println("director = " + director);

        Assertions.assertTrue(director.equals(expectedDirector));





        String actors = response.path("Actors");

        String expectedInActor = "Alec Baldwin";

        System.out.println("actors = " + actors);

        Assertions.assertTrue(actors.contains(expectedInActor));



        String rating = response.path("Ratings[0].Value");

        String actualRating = "6.3/10";

        System.out.println("rating = " + rating);
        
        Assertions.assertTrue(actualRating.equals(rating));


    }


}
