package day05;

import static  io.restassured.RestAssured.* ;

import Pojo.Locations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class hr_ords_locationsPojoTest {

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

        baseURI = "http://35.153.51.63";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("testing  the /locations/{id} endpoint")
    @Test
    public void testLocations(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
                pathParam("location_id", 2000).
        when().
                get("/locations/{location_id}");


        Locations locations = response.as(Locations.class);

        System.out.println("locations = " + locations);


    }

    // send a request to /locations endpoint to get all locations
    // save the locations json array into pojo List
    // you already have taken care of unknown properties so no extra action needed
    // just call the method of jsonPath object to get the list you want

    @DisplayName("testing the /locations endpoint")
    @Test
    public void testLocationJsonArrayToPojoList(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON).
        when().
                get("/locations");

        JsonPath jsonPath = response.jsonPath();

        List<Locations> locationsList = jsonPath.getList("items", Locations.class);

        System.out.println("locationsList = " + locationsList);


    }


    @Test
    public void testLocationsGetSingleItem(){

        /*
    private int location_id;
    private String street_address;
    private String postal_code;
    private String city;
    private String state_province;
         */

        Response response =  get("/locations");

        JsonPath jsonPath = response.jsonPath();

        // Get first location_id from the response using jsonPath
        System.out.println("first location_id from responseJson = " + jsonPath.getInt("items[0].location_id"));

        // Get first street_address from the response using jsonPath
        System.out.println("first street_address from responseJson = " + jsonPath.getString("items[0].street_address"));

        // Get first postal_code from the response using jsonPath
        System.out.println("first postal code from responseJson = " + jsonPath.getString("items[0].postal_code"));

        // Get first  city from the response using jsonPath
        System.out.println("first city  from responseJson = " + jsonPath.getString("items[0].city"));

        // Get first  state_province  from the response using jsonPath
        System.out.println("first state_province from responseJson = " + jsonPath.getString("items[0].state_province"));

        // Get last  location_id from the response using jsonPath
        System.out.println("last location id from responseJson = " + jsonPath.getInt("items[-1].location_id"));

        // Get last  street_address from the response using jsonPath
        System.out.println("last street_address from responseJson = " + jsonPath.getString("items[-1].street_address"));

        // Get last postal_code from the response using jsonPath
        System.out.println("last postal code from responseJson = " + jsonPath.getString("items[-1].postal_code"));

        // Get last city  from the response using jsonPath
        System.out.println("last city from responseJson = " + jsonPath.getString("items[-1].city"));

        // Get last state_province from the response using jsonPath
        // or use -1 for index to get last one
        System.out.println("last state province from responseJson = " + jsonPath.getString("items[-1].state_province"));


        // get the list of city from the response and save it to List<String>
        List<String> listCity = jsonPath.getList("items.city");

        for(String each : listCity){

            System.out.println(each);
        }

        System.out.println("====================================================");


        // get a List<Locations> from the response json
        List<Locations> locationsList = jsonPath.getList("items");

        for(int i = 0; i < locationsList.size(); i++){

            System.out.println(locationsList.get(i));
        }




    }





}
