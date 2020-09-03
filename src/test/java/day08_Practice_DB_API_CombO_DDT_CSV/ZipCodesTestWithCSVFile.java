package day08_Practice_DB_API_CombO_DDT_CSV;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;

public class ZipCodesTestWithCSVFile {
    /*
    --- Data Drive your GET / api.zippopotam.us/us/:state/:city
    --- Create a csv file called state_city.csv
        add 3 column  state , city , numberOfZipcodes
                      VA ,  Fairfax , 9(send the request and prepare this number)
         assert the state , city  and number of zipcodes you got from the response

     */

    @BeforeAll
    public static void setUp(){

        //api.zippopotam.us/us/:zipcode
        RestAssured.baseURI = "http://api.zippopotam.us";
        basePath = "/us";

    }


    @ParameterizedTest(name = "iteration {index} | state : {0}, city : {1}, numberofZipcodes : {2} ")
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testZipCodesWithCSVFile(String state, String city, int numberOfZipcodes){

        Response response =


        given().
                pathParam("state", state).
                pathParam("city",city).
        when().
                get("/{state}/{city}");

        //state/:city

        String place =  response.jsonPath().getString("places[0].'place name'");

        System.out.println("place = " + place);
    }


}
