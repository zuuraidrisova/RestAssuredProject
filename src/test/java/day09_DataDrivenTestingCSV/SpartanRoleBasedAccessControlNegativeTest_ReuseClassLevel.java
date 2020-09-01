package day09_DataDrivenTestingCSV;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SpartanRoleBasedAccessControlNegativeTest_ReuseClassLevel {



    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;

    /*
    If your application API have lots of repeating part in the request and response
assertion, you can create a utility class for different type of request spec and response spec,
to re-use in your tests everywhere and it will make it easy to write and maintain in the long run.
     */

    @BeforeAll
    public static void init(){

        //we are using Akbar's ip address because it is with basic auth,
        // and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        requestSpec = given().

                auth().basic("user", "user").
                accept(ContentType.JSON);

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        // Getting the reusable ResponseSpecification object using the builder methods chaining
        responseSpec = responseSpecBuilder.expectStatusCode(403).
                expectContentType(ContentType.JSON).
                expectHeader("Date",notNullValue(String.class) ).
                log(LogDetail.ALL).
                build();

    }

    @DisplayName("User should not be able to delete data using requestSpec and responseSpec")
    @Test
    public void testUserCannotDeleteData(){

        given().
                spec(requestSpec).
                when().
                delete("/spartans/{id}",10).
                then().
                spec(responseSpec);

    }

    @DisplayName("User should not be able to update data using requestSpec and responseSpec ")
    @Test
    public void testUserCannotUpdateData() {

        Spartan spartan = new Spartan("Hailo", "Female", 89721527121l);

        given().
                spec(requestSpec).
                contentType(ContentType.JSON).
                body(spartan).
        when().
                put("/spartans/{id}", 10).
        then().
                spec(responseSpec);


    }

    @DisplayName("User should not be able to add data using requestSpec and responseSpec ")
    @Test
    public void testUserCannotPostData() {

        Spartan spartan = new Spartan("Haydi", "Female", 899721527121l);

        given().
                spec(requestSpec).
                contentType(ContentType.JSON).
                body(spartan).
        when().
                post("/spartans" ).
        then().
                spec(responseSpec);


    }

}
