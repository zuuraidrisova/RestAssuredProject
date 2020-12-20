package day09_DataDrivenTestingCSV;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBasedAccessControlNegativeTest_Reuse {

    @BeforeAll
    public static void init(){

        //we are using Akbar's ip address because it is with basic auth,
        // and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCannotDeleteData(){

        //building reusable request specification

        RequestSpecification   requestSpec = given().

                         auth().basic("user", "user").
                         accept(ContentType.JSON);

        // Extracting ResponseSpecification for all assertions so we can reuse
      // We will be using a class called ResponseSpecBuilder
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        // Getting the reusable ResponseSpecification object using the builder methods chaining
        ResponseSpecification responseSpec = responseSpecBuilder.expectStatusCode(403).
                             expectContentType(ContentType.JSON).
                             expectHeader("Date",notNullValue(String.class) ).
                             log(LogDetail.ALL).
                              build();

        //expectHeader second argument expect a Matcher<String>
        //        // but notNullValue() return a Matcher<Object> so it did not compile
        //        // so we used the second version of notNullValue( The Matcher type inside <>)
        //        // to specify what kind of matcher we wanted


        given().
                spec(requestSpec).
        when().
                delete("/spartans/{id}",10).
        then().
              spec(responseSpec);


    }

    @AfterAll
    public static void tearDown(){

        RestAssured.reset();
    }




}
