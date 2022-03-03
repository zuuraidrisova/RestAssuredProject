package day02_Parameters;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class movieAPI {

    /*
    // make a request to http://www.omdbapi.com
	      by adding few query parameters like
	      apikey =  26aa5b74
	      t =  the movie you want to search
	      plot =  full
	      then status code 200
	      		contentype is json
	      		body
	      			title is what you are searching for
	      			year is as you expected
	      			first rating value
	      			last rating value
     */

    @BeforeAll
    public static void setUp(){

        baseURI = "http://www.omdbapi.com";

    }


    @DisplayName("testing movie API with query params")
    @Test
    public void testQueryParam(){

        given().
                queryParam("apikey","26aa5b74").
                queryParam("t", "The next three days").
                queryParam("plot", "full" ).
                log().all().
         when().
                get(). //what if my url already complete, then do nothing
        then().
                statusCode(is(200)).
                contentType(ContentType.JSON).
                body("Title",is("The Next Three Days")).
                body("Year", equalTo("2010")).
                body("Ratings[0].Value", is("7.3/10")).
                body("Ratings[2].Value", is("52/100"));


    }



}
