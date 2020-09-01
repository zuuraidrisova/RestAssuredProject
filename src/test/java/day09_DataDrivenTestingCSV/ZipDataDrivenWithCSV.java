package day09_DataDrivenTestingCSV;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipDataDrivenWithCSV {

    /*
    Data Drive your GET / api.zippopotam.us/us/:state/:city
    --- Create a csv file called state_city.csv
        add 3 column  state , city , numberOfZipcodes
                      VA ,  Fairfax , 9(send the request and prepare this number)
         assert the state , city
                    and number of zipcodes you got from the response
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testStateCity(String expectedState, String expectedCity, int expectedNumberOfZipcodes) {

        //api.zippopotam.us/us/:state/:city

        Response response =

        given().
                baseUri("http://api.zippopotam.us/us").
        when().
                get("/{state}/{city}", expectedState, expectedCity);
               // prettyPeek();

        //assert state, city


        JsonPath jsonPath = response.jsonPath();

        // why do we use single quote? because there can not be a space in json path
        // we are using the '' to treat the 2 word as one

        String actualState = jsonPath.getString(" 'state abbreviation' ");

        System.out.println("stateAbbreviation = " + actualState);

        String actualCity = jsonPath.getString(" 'place name' ");

        System.out.println("cityName = " + actualCity);

        // asserting actual result from response to expected result from csv file

        assertThat(expectedState, equalTo( actualState));

        assertThat(expectedCity, is(actualCity));

        // now we want to count how many item in jsonArray from the respons e
        // and validate that against our csv file expected numbers
        // since post code key has space in between we have to add '' to treat it as one

        List<String> zipList = jsonPath.getList("places.'post code'");

        System.out.println("zipList = " + zipList);

        assertThat(zipList, hasSize(expectedNumberOfZipcodes));

        // OPTIONALLY YOU MAY DO AS BELOW TO COUNT YOUR JSON ARRAY
        // if your jsonpath is pointing to an jsonArray you can count them
        // by called groovy method called size()
        System.out.println("calling the size method directly in json = " + jsonPath.getInt("places.size()"));


    }

    @Test
    public void testSingle(){

        Response response =

                given().
                        pathParam("state", "VA").
                        pathParam("city", "FairFax").
                        baseUri("http://api.zippopotam.us/us").
                        when().
                        get("/{state}/{city}");


        response.then().statusCode(is(200));

    }

}
