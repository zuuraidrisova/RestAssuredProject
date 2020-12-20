package day05_POJO_Authentication;

import pojo.Jobs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class hr_ords_jobsPojoTest {


    @BeforeAll
    public static void setUp(){

        /*
          Here is the get request we need to make
              http://35.153.51.63:1000/ords/hr/regions/1
              baseURI = http://35.153.51.63
              port = 1000
              basePath = ords/hr
              request :  GET /regions/{region_id}
         */

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";


    }

    @DisplayName("testing the /jobs/{job_id} endpoints")
    @Test
    public void testCountries(){

        Response response =

                given().
                        log().all().
                        contentType(ContentType.JSON).
                        pathParam("job_id", "AC_ACCOUNT").
                when().
                        get("/jobs/{job_id}");

        Jobs jobs = response.as(Jobs.class);

        System.out.println("jobs = " + jobs);

    }

    // send a request to /jobs endpoint to get all countries
    // save the jobs json array into pojo List
    // you already have taken care of unknown properties so no extra action needed
    // just call the method of jsonPath object to get the list you want

    @DisplayName("testing the /jobs endpoint")
    @Test
    public void testJobsJsonArrayToPojoList(){

        Response response =

                given().
                        contentType(ContentType.JSON).
                when().
                        get("/jobs");

        JsonPath jsonPath = response.jsonPath();

        List<Jobs> jobsList = jsonPath.getList("items", Jobs.class);

        for(Jobs each : jobsList){

            System.out.println(each);
        }

    }



    @Test
    public void testJobsGetSingleItem(){

        Response response = get("/jobs");

        JsonPath jsonPath = response.jsonPath();

       // Get first job_title from the response using jsonPath
        System.out.println("first job_title from jsonResponse = " + jsonPath.getString("items[0].job_title"));

        // Get first job_id from the response using jsonPath
        System.out.println("first job_id from jsonResponse = " + jsonPath.getString("items[0].job_id"));

        //Get first min salary from the response using jsonPath
        System.out.println("first min salary from jsonResponse = " + jsonPath.getString("items[0].min_salary"));

        //Get first max salary from the response using jsonPath
        System.out.println("first max salary from jsonResponse = " + jsonPath.getString("items[0].max_salary"));

        // Get last job_title from the response using jsonPath
        System.out.println("last job_title from jsonResponse = " + jsonPath.getString("items[-1].job_title"));

        // Get last job_id from the response using jsonPath
        System.out.println("last job_id from jsonResponse = " + jsonPath.getString("items[-1].job_id"));

        //Get last min salary from the response using jsonPath
        System.out.println("last min salary from jsonResponse = " + jsonPath.getString("items[-1].min_salary"));

        //Get last max salary from the response using jsonPath
        // or use -1 for index to get last one
        System.out.println("last max salary from jsonResponse = " + jsonPath.getString("items[-1].max_salary"));

        // get the list of job title from the response and save it to List<String>
        List<String> jobTitleList = jsonPath.getList("items.job_title");

        for (String each : jobTitleList){

            System.out.println(each);
        }

        System.out.println("===================================================");

        // get a List<Jobs> from the response json
        List<Jobs> jobsList = jsonPath.getList("items");

        System.out.println("jobsList = " + jobsList);


    }






}
