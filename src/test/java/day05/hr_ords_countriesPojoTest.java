package day05;

import Pojo.Countries;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class hr_ords_countriesPojoTest {

     /*
          Here is the get request we need to make
              http://35.153.51.63:1000/ords/hr/regions/1
              baseURI = http://35.153.51.63
              port = 1000
              basePath = ords/hr
              request :  GET /regions/{region_id}
         */


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("testing the /countires/{country_id} endpoints")
    @Test
    public void testCountries(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
                pathParam("country_id", "AR").
        when().
                get("/countries/{country_id}");

        Countries countries = response.as(Countries.class);


        System.out.println("countries = " + countries);


    }

    // send a request to /countries endpoint to get all countries
    // save the countries json array into pojo List
    // you already have taken care of unknown properties so no extra action needed
    // just call the method of jsonPath object to get the list you want

    @DisplayName("testing the /countries endpoint")
    @Test
    public void testCountriesJsonArrayToPojoList(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
        when().
                get("/countries");

        JsonPath jsonPath = response.jsonPath();

        List<Countries> countriesList = jsonPath.getList("items", Countries.class);

        for(Countries each : countriesList){

            System.out.println(each);
        }

    }


    @Test
    public void testCountriesGetSingleItem(){

        Response response =

                given().
                        contentType(ContentType.JSON).
                when().
                        get("/countries");

        JsonPath jsonPath = response.jsonPath();

        // Get first country_name from the response using jsonPath
        System.out.println("first country name from response Json = " + jsonPath.getString("items[0].country_name"));

        // Get first country_id from the response using jsonPath
        System.out.println("first country-id  from response Json = " + jsonPath.getString("items[0].country_id"));

        // Get last country_name from the response using jsonPath
        System.out.println("last country name from response Json = " + jsonPath.getString("items[-1].country_name"));

        // Get last country_id from the response using jsonPath
        // or use -1 for index to get last one
        System.out.println("last country-id from response Json = " + jsonPath.getString("items[0].country_id"));

        // get the list of country name from the response and save it to List<String>
        List<String> listCountryNames = jsonPath.getList("items.country_name");

        for (String each: listCountryNames){

            System.out.println(each);
        }

        System.out.println("====================================================");

        // get a List<Countries> from the response json
        List<Countries> countriesList = jsonPath.getList("items");

        System.out.println("countriesList = " + countriesList);
    }








}
