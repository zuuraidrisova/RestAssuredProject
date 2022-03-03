package day06_AuthenticationAuthorization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {


    //posting to library app
    // body is not json , it's x-www-urlencoded-form-data

    //https://library2.cybertekschool.com/rest/v1/login
    // baseURI  is https://library2.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // Post body , type x-www-urlencoded-form-data
    //email : librarian123455@library
    //password : QoyNEHxI

    private static String LibraryToken;


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://library2.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";

        LibraryToken = loginAndGetToken("librarian123455@library","QoyNEHxI");

    }


    @DisplayName("Send a request to /DashboardStat")
    @Test
    public void testDashboardStatWithAuthorization(){

        given().
                log().all().
                header("x-library-token", LibraryToken).
        when().
                get("/dashboard_stats").
        then().
                log().all().
                statusCode(is(200)).
                body("book_count", is("23459")).
                body("borrowed_books", is("4453")).
                body("users", is("7396"));

    }

    //add a test for the POST /decode endpoint
    // this endpoint does not need authorization
    // it accept form param as name token value your long token
    // and return json response as user information and authority
    // assert the email of user is same as the email you used the token
    @DisplayName("Testing  port /decode endpoint")
    @Test
    public void testDecodeJWT_Token(){

        given().
                log().all().
                contentType(ContentType.URLENC).//this specify what kind of data u r sending to the server as body
                accept(ContentType.JSON).//this is telling i want json back as response
                formParam("token", LibraryToken).
        when().
                post("/decode").
        then().
                log().all().
                statusCode(is(200)).
                body("email", is("librarian123455@library")).
                body("token", is(LibraryToken));



    }



    @DisplayName("Test /get_user_by_id/2080 endpoint")
    @Test
    public void testGetUserByIDEndpoint(){

        given().
                log().all().
                header("x-library-token", LibraryToken).
                pathParam("id", 2080).
                accept(ContentType.JSON).
        when().
                get("/get_user_by_id/{id}").
        then().
                log().all().
                statusCode(is(200)).
                body("id",is("2080")).
                body("full_name", is("dfasdf")).
                body("email", equalTo("fasf@fa.com")).
                body("password", is("c7d48bbf2b960adc10b0aba11bf336a5")).
                body("user_group_id", is("3")).
                body("image", is(nullValue())).
                body("extra_data", is(nullValue())).
                body("status", is("ACTIVE")).
                body("is_admin", is("0")).
                body("start_date", is("2020-11-06")).
                body("end_date", is("2020-12-06")).
                body("address", equalTo("afafasdfdasa"));

    }

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password){

        Response jsonResponse  =

        given().

                contentType(ContentType.URLENC).
                formParam("email", username ).
                formParam("password", password).
         when().
                post("/login");


        JsonPath jsonPath = jsonResponse.jsonPath();

        String token = jsonPath.getString("token");

        return  token;

    }





}
