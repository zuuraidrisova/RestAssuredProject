package day12_SchemaValidations_XML;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class OpenMovieDB_XMLPath {

    //http://www.omdbapi.com/?apikey=26aa5b74&r=xml&s=Superman

    @DisplayName("getting many movies and extracting title attribute to the list")
    @Test
    public void testGettingAttributeAsList(){

        Response response =

        given().
                baseUri("http://www.omdbapi.com/").
                queryParam("apikey","26aa5b74").
                queryParam("r","xml").
                queryParam("s", "Superman").
        when().
                get("");

        XmlPath xmlPath = response.xmlPath();

        List<String> listOfSupermanMovies = xmlPath.getList("root.result.@title");

        System.out.println("listOfSupermanMovies = " + listOfSupermanMovies);


    }



}
