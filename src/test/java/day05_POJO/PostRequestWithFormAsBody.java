package day05_POJO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostRequestWithFormAsBody {


    @BeforeAll
    public static void setUp(){

        //posting in library app
        // body is not json , it's x-www-urlencoded-form-data

        //http://library1.cybertekschool.com/rest/v1/login
        // baseURI  is http://library1.cybertekschool.com
        // basePath is /rest/v1
        // we are working on POST /login

        // Post body , type x-www-urlencoded-form-data
        //email :    librarian69@library
        //password : KNPXrm3S

        // in URLs if you do not see port , because it's omitted because it's so common
        //  http --->> 80
        //  https --->> 443
        //  anything other than above 2 ports need to be explicitly set in the URL
        // for example spartan app use port 8000 -->> yourip:8000

        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";



    }

    @DisplayName("test post/login endpoint")
    @Test
    public void testLoginEndPoint(){


        given().
                log().all().
                formParam("email", "librarian69@library").
                formParam("password", "KNPXrm3S").
        when().
                post("/login").
        then().
                log().all().
                statusCode(is(200)).
  //we cannot validate token since it is dynamic, but we can see whether it is null or not null
                body("token", is(notNullValue()));



    }

       /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password){

        Response jsonResponse  = given().
                contentType(ContentType.URLENC).
                formParam("email", username ).
                formParam("password", password).
                when().
                post("/login");

        JsonPath jsonPath = jsonResponse.jsonPath();

        String token = jsonPath.getString("token");

        return  token;
    }


    @DisplayName("testing the utility")
    @Test
    public void runningUtilityMethod(){

       String token =  loginAndGetToken("librarian69@library", "KNPXrm3S");

        System.out.println("token = " + token);

    }



}
