package day12_SchemaValidations_XML;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenDBMovie_XML {

    //http://www.omdbapi.com/?t=Boss Baby&r=xml
    //apikey is 26aa5b74

    @Test
    public void testMovieXML(){


        given().
                log().all().
                baseUri("http://www.omdbapi.com").
                queryParam("apikey", "26aa5b74").
                queryParam("r", "xml").
                queryParam("t","Boss Baby").
        when().
                get("").
        then().
                log().all().
                statusCode(is(200)).
                contentType(ContentType.XML).
                //the result has only 2 elements, parent : root and child: movie
                //all the movie data is stored under movie attributes
                body("root.movie.@title", is("The Boss Baby")).
                body("root.movie.@year", is("2017")).
                body("root.movie.@rated",is("PG")).
                body("root.movie.@released", is("31 Mar 2017"));

    }

    @AfterAll
    public static void tearDown(){

        RestAssured.reset();
    }


}
