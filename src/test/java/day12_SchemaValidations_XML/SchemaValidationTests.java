package day12_SchemaValidations_XML;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SchemaValidationTests {

    /*
    JsonSchema Validation Warm up :
Add the AllSpartansSchema.json and SearchSchema.json under resources forlder
Create a class called SchemaValidationTest
Add 2 tests:
GET /spartans
GET /spartans/search
Validate the response against the schema in classpath
     */


    @BeforeAll
    public static void setUp(){


        baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }


    @DisplayName("testing json format ==> json schema validation")
    @Test
    public void testAllSpartansJsonSchema(){


        given().
                log().uri().
        when().
                get("/spartans").
                //prettyPeek().
        then().
                body(matchesJsonSchemaInClasspath("AllSpartanSchema.json"));


    }


    @DisplayName("testing Get /spartans/search response against schema")
    @Test
    public void testSearchJsonSchema(){

        // search female in query param and validate response against schema

        given().
                queryParam("gender", "Female").
        when().
                get("/spartans/search").
                //prettyPeek().
        then().
                body(matchesJsonSchemaInClasspath("SearchSchema.json"));


    }

    /*
    what if my schema file is not under resources folder ?
     then matchesJsonSchemaInClasspath  method will not work because
     it only look for schema under resources folder.
        We have to use matchesJsonSchema method and provide full path
        if file is not under resource folder
     */

    @DisplayName("test Get /spartans against schema using root level json")
    @Test
    public void testAllSpartansJsonSchema2(){

        //create a File object that point to the schema
        //use matchesJsonSchema() method that accepts a file and do ur validation

        File file = new File("AllSpartanSchema2.json");

        given().
                log().uri().
        when().
                get("/spartans").
                //prettyPeek().
        then().
                body(matchesJsonSchema(file));


    }



    @AfterAll
    public static void tearDown(){

        RestAssured.reset();
    }


}
