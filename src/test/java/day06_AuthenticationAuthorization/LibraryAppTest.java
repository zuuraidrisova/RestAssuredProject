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

    private static String LibraryToken;



    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";
        LibraryToken = loginAndGetToken("librarian69@library","KNPXrm3S");

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
                body("book_count", is("985")).
                body("borrowed_books", is("601")).
                body("users", is("5042"));

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
                body("email", is("librarian69@library")).
                body("token", is(LibraryToken));



    }

    @DisplayName("Test /Get user by id endpoint")
    @Test
    public void testSingleUserDate(){

        given().
                log().all().
                header("x-library-token", LibraryToken).
                pathParam("id", 2080).
                accept(ContentType.JSON).
        when().
                get("/get_user_by_id/{id}").
        then().
                statusCode(is(200)).
                body("id", equalTo("2080")).
                body("full_name", is("Test Student 142")).
                body("user_group_id", is("3")).
                body("status", equalTo("ACTIVE")).
                body("is_admin", is("0")).
                body("start_date", is("2020-06-22")).
                body("end_date", is("2021-06-22")).
                body("address", is("Test address 142")).
                body("image",  is(nullValue()));


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





}
