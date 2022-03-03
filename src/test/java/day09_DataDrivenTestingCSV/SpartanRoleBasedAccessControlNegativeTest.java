package day09_DataDrivenTestingCSV;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBasedAccessControlNegativeTest {
    /*
    -------  We are doing a role based access control test
     -- for the Spartan app with username password
        for the credentials  user/user

       user should not be able to delete data
       user should not be able to post data
       user should not be able to update data

       all these 3 tests share same username and password
       and we can also add accept json result back
       and we want to log all the request

        all these test can share same response status as 403
        and all tests response content type is json
        and all test has Date header not null assertion
        and we want to see the log of all request
     */

    @BeforeAll
    public static void init(){

        //we are using Akbar's ip address because it is with basic auth,
        // and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.236.150.168";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCannotDeleteData(){

        given().
                auth().basic("user","user").
                accept(ContentType.JSON). //we want json result back
                log().all().
        when().
                delete("/spartans/{id}", 950).
        then().
                log().all().
                statusCode(is(403)).
                contentType(ContentType.JSON).
                header("Date", is(notNullValue())); //checking the date header is not null


    }



    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCannotUpdateData(){

        Spartan spartan = new Spartan("some name", "Male", 888888888l);

        given().
                auth().basic("user","user").
                accept(ContentType.JSON). //we want json result back
                body(spartan).
                log().all().
        when().
                put("/spartans/{id}",950).
         then().
                log().all().
                statusCode(is(403)).
                contentType(ContentType.JSON).
                header("Date", is(notNullValue())); //checking the date header is not null



    }


    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCannotPostData(){


        Spartan spartan = new Spartan("some name", "Male", 888888888l);

        given().
                auth().basic("user","user").
                accept(ContentType.JSON). //we want json result back
                body(spartan).
                log().all().
        when().
                post("/spartans").
        then().
                log().all().
                statusCode(is(403)).
                contentType(ContentType.JSON).
                header("Date", is(notNullValue())); //checking the date header is not null


    }



}
