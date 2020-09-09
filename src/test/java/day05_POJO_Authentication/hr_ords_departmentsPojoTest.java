package day05_POJO_Authentication;

import pojo.Departments;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class hr_ords_departmentsPojoTest {

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

    //Serialization : pojo to json  and
    //De-Serialization : json to pojo

    @DisplayName("testing the /departments/{department_id} endpoints")
    @Test
    public void testDepartments(){


        Response  response =

        given().
                log().all().
                contentType(ContentType.JSON).
                pathParam("department_id", 10).
        when().
                get("/departments/{department_id}");


        Departments departments = response.as(Departments.class);

        System.out.println("departments = " + departments);

    }


    // send a request to /departments endpoint to get all countries
    // save the departments json array into pojo List
    // you already have taken care of unknown properties so no extra action needed
    // just call the method of jsonPath object to get the list you want

    @DisplayName("testing the /departments endpoint")
    @Test
    public void testDepartmentsJsonArrayToPojoList(){

        Response response =

        given().
                log().all().
                contentType(ContentType.JSON) .
        when().
                get("/departments");


        JsonPath jsonPath = response.jsonPath();


        List<Departments> departmentsList = jsonPath.getList("items", Departments.class);

        //System.out.println("departmentsList = " + departmentsList);

        for(Departments each : departmentsList){

            System.out.println(each);
        }


    }



    @Test
    public void testDepartmentsGetSingleItem(){

        Response response = get("/departments");

        JsonPath jsonPath = response.jsonPath();

        // Get first department_id from the response using jsonPath
        System.out.println("first department_id from response json  = " + jsonPath.getInt("items[0].department_id"));

        // Get first department_name from the response using jsonPath
        System.out.println("first department_name from response json = " + jsonPath.getString("items[0].department_name"));

        // Get first manager_id from the response using jsonPath
        System.out.println("first manager_id from response json = " + jsonPath.getInt("items[0].manager_id"));

        // Get last department_id from the response using jsonPath
        System.out.println("last department_id from response json  = " + jsonPath.getInt("items[-1].department_id"));

        // Get last department_name from the response using jsonPath
        // or use -1 for index to get last one
        System.out.println("last department_name from response json = " + jsonPath.getString("items[-1].department_name"));

        // Get last manager_id from the response using jsonPath
        //System.out.println("last manager_id from response json = " + jsonPath.getInt("items[-1].manager_id"));
        //this is giving runtime exception because manager_id is null, not an int

        // get the list of department name from the response and save it to List<String>
         List<String> listDepartmentNames = jsonPath.getList("items.department_name");

         for(int i = 0; i <  listDepartmentNames.size(); i++){

             System.out.println(listDepartmentNames.get(i));
         }

        // get a List<Departments> from the response json
        List<Departments> departmentsList = jsonPath.getList("items");

        System.out.println("departmentsList = " + departmentsList);


    }




}
