package day10_RequestSpecResponseSpec;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Users;
import utility.ConfigurationReader;
import utility.LibraryAPI_Utility;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class De_serialization_usingStaticSpec {

    /*
    Practice the De-Serialization using the same test
get the Map<String,Integer> object out of the response of GET /dashboard_stats
get the List<Category> object from the response of GET /get_book_categories
get the List<User> object from the response of GET /get_all_users
hint : you will need to create 2 POJO class called Category , User;
     */

    static String libraryToken;


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "rest/v1";


        libraryToken = LibraryAPI_Utility.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username"),
                ConfigurationReader.getProperty("library1.librarian_password"));

        //just like baseUri and basePath, we can use static fields of RestAssured
        //to set it at global level
        RestAssured.requestSpecification = given().
                accept(ContentType.JSON). //we want json back
                log().all().
                header("x-library-token", libraryToken);


        //ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        //is there easy way of building responsesPEC OBJECT WITHOUT BUILDER? yes, BELOW

        RestAssured.responseSpecification =  expect().statusCode(is(200)).
                contentType(ContentType.JSON).
                logDetail(LogDetail.ALL);

    }


    //get the List<Category> object from the response of GET /get_book_categories

    @DisplayName("testing get_book_categories Endpoint with Spec")
    @Test
    public void testGet_book_categories(){

        Response response =

        when().
                get("/get_book_categories").prettyPeek();

        JsonPath jsonPath = response.jsonPath();

        //here we are saying turn json to pojo
        List<Category> listOfCategories = jsonPath.getList("", Category.class);

        System.out.println("listOfCategories = " + listOfCategories);

        //above code is great, but what if i wanted
        // to store each category as map rather than pojo
        //each category is key value pair so we can store it as map <Map>
        //and we have many category , we can store it as list of map List<Map>

        //here we are saying turn json to list of maps
        List<Map<String, String>> listOfMap = response.jsonPath().getList("");

        System.out.println("listOfMap = " + listOfMap);


    }


    //get the List<User> object from the response of GET /get_all_users

    @DisplayName("testing get_all_users Endpoint with Spec")
    @Test
    public void testGetAllUsers(){

        Response response =

        when().
                get("/get_all_users");

        JsonPath jsonPath = response.jsonPath();

        List<Users> listOfUsers = jsonPath.getList("", Users.class);

        System.out.println("listOfUsers = " + listOfUsers);


    }

    //get the Map<String,Integer> object out of the response of GET /dashboard_stats

    @DisplayName("testing /dashboard_status Endpoint with Spec")
    @Test
    public void test_dashboard_status(){

        Response response =

        when().
                get("/dashboard_stats").prettyPeek();

        JsonPath jsonPath = response.jsonPath();

       Map<String, Integer> dashBoardStats = jsonPath.getMap("");

        System.out.println("dashBoardStats = " + dashBoardStats);

    }



    @AfterAll
    public static void tearDown(){

        RestAssured.reset();

    }


}
