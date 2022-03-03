package day05_POJO_Authentication;

import pojo.Region;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class hr_ords_regions_testWithPojo {

        /*
          Here is the get request we need to make
              http://54.236.150.168:1000/ords/hr/regions/1
              baseURI = http://54.236.150.168
              port = 1000
              basePath = ords/hr
              request :  GET /regions/{region_id}
         */

        @BeforeAll
        public  static  void setUp(){

            baseURI = "http://54.236.150.168";
            port = 1000;
            basePath = "ords/hr";


        }

        @DisplayName("testing  the /regions/{id} endpoint")
        @Test
        public void testRegion(){


            Response response =

            given().
                    log().all().
                    accept(ContentType.JSON).
                    pathParam("id", 1).
            when().
                    get("/regions/{id}");


            Region region = response.as(Region.class);

            System.out.println("region = " + region);

        }


        @DisplayName("testing the /regions endpoint")
        @Test
        public void testRegionJsonArrayToPojoList(){


            // send a get request to /regions endpoint to get all regions
            // save the regions from json array into pojo List
            // you already have taken care of unknown properties so no extra action needed
            // just call the method of jsonPath object to get the list you want

            Response response =

            given().
                    log().all().
                    accept(ContentType.JSON).
            when().
                    get("/regions");


            JsonPath jsonPath = response.jsonPath();

            List<Region> regionList = jsonPath.getList("items", Region.class);

            System.out.println("regionList = " + regionList);



        }


    @Test
    public void testRegionGetSingleItems(){

        Response response = get("/regions");

        JsonPath jsonPath = response.jsonPath();

        // Get first region_name from the response using jsonPath
        System.out.println("first region name = " + jsonPath.getString("items[0].region_name"));

        // Get first region_id from the response using jsonPath
        System.out.println("first region id = " + jsonPath.getInt("items[0].region_id"));

        // Get last region_name from the response using jsonPath
        System.out.println("last region name = " + jsonPath.getString("items[-1].region_name"));

        // Get last region_id from the response using jsonPath
        // or use -1 for index to get last one
        System.out.println("last region id = " + jsonPath.getInt("items[-1].region_id"));

        // get the list of region name from the response and save it to List<String>
        List<String> listRegionName = jsonPath.getList("items.region_name");

        System.out.println("listRegionName = " + listRegionName);

        // get a List<Region> from the response json
        List<Region> regionList = jsonPath.getList("items", Region.class);

        System.out.println("regionList = " + regionList);



    }



}
