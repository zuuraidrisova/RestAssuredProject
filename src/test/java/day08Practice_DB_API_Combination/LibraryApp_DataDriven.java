package day08Practice_DB_API_Combination;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_DataDriven {
    /*
    --- Create a csv file under resources folder called credentials.csv
    -- it has 2 column , username , password
    --- copy the library1 username password I shared under codenote to create this file
    ----
    We will write a parameterized test for POST /login endpoint
    if the username and password is valid
        you should simply get 200 and the token field should not be null
     */

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        //RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_path");
        RestAssured.basePath ="/rest/v1";
    }


    //give a name to ur test in this format
    //iteration {index} | username : {firstColumn}, password: {secondColumn}

    @ParameterizedTest(name = "iteration {index} | username : {0}, password : {1}")
    @CsvFileSource(resources = "/credentials.csv", numLinesToSkip = 1)
    public void testLoginCredentials(String username, String password){


        //this is just for reading csv file
        System.out.println("Username and password : "+username +" ==>  "+password);

        //now post request to /login endpoint
        //contentType is contentType(ContentType.URLENC).
        //form data is  email, password
        //check if status code is 200 if password is correct
        //check the token field from response is not null


        given().
                contentType(ContentType.URLENC).
                formParam("email", username).
                formParam("password", password).
        when().
                post("/login").

        then().
                statusCode(is(200)).
                body("token", is(notNullValue()));



    }


    @AfterAll
    public static void tearDown(){

        RestAssured.reset();

    }




}
