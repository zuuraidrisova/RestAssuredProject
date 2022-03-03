package day10_RequestSpecResponseSpec;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.LibraryAPI_Utility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppReusingSpec_Shorter {


    /*
    We will use these 3 endpoints :
* GET /dashboard_stats
* GET /get_book_categories
* GET /get_all_users
We want to save the Request spec for
  * setting the X-LIBRARY-TOKEN HEADER
  * ContentType Header
  * logging everything
We want to save the Response spec for
  * Status code of `200`
  * ContentType Header is JSON
  * log if validation fail
     */


//    static RequestSpecification requestSpec ;
//    static ResponseSpecification responseSpec ;
    static String libraryToken;

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library2.base_url");
        RestAssured.basePath = "rest/v1";


        libraryToken = LibraryAPI_Utility.loginAndGetToken(ConfigurationReader.getProperty("library2.librarian_username"),
                ConfigurationReader.getProperty("library2.librarian_password"));

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



    @DisplayName("testing get_book_categories Endpoint with Spec")
    @Test
    public void testGet_book_categories(){


                when().
                get("/get_book_categories");


    }


    @DisplayName("testing get_all_users Endpoint with Spec")
    @Test
    public void testGetAllUsers(){

                when().
                get("/get_all_users");

    }


    @DisplayName("testing /dashboard_status Endpoint with Spec")
    @Test
    public void test_dashboard_status(){


                when().
                get("/dashboard_stats");


    }


    @AfterAll
    public static void tearDown(){

        RestAssured.reset();

    }

}
