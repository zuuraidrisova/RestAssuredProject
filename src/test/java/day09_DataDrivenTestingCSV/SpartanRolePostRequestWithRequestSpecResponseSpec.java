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
import utility.SpartanAPI_Utility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpartanRolePostRequestWithRequestSpecResponseSpec {

    /*
    //   Practice the requestSpec and response Spec with POST /Spartans endpoint
//   extract out the request specification for
            authentication (admin/admin)
            logging (all of them)
            contentType (json)
            randomBody (created from createRandomSpartan method)
//  extract out the responseSpec
            statusCode (201)
            Date (not null like previous class)
            body
                 "success": "A Spartan is Born!",
                 id is not null
                 name is the name we used to create the Spartan object
                 gender is the gender we used to create the Spartan object
                 phone is the phone we used to create the Spartan object
     */

    static RequestSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec;

    @BeforeAll
    public static void setUp(){

        //we are using Akbar's ip address because it is with basic auth,
        // and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.236.150.168";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";


        Spartan spartan = SpartanAPI_Utility.createRandomSpartanObject();

        validPostRequestSpec = given().
                accept(ContentType.JSON). //to tell server what type of file u want  back
                contentType(ContentType.JSON).//to tell server what type of file u r sending
                body(spartan).
                log().all();

        ResponseSpecBuilder  responseSpecBuilder = new ResponseSpecBuilder();

        validPostResponseSpec = responseSpecBuilder.log(LogDetail.ALL).
                expectStatusCode(is(201)).
                expectContentType(ContentType.JSON).
                expectHeader("Date", is(notNullValue(String.class))).
                expectBody("success",  is("A Spartan is Born!" )).
                expectBody("data.name", equalTo(spartan.getName())).
                expectBody("data.gender", equalTo(spartan.getGender())).
                expectBody("data.phone", equalTo(spartan.getPhone())).
                expectBody("data.id", is(notNullValue())).
                build();


    }


    @DisplayName("Extracting the requestSpec and responseSpec practice")
    @Test
    public void test(){


        given().
                spec(validPostRequestSpec).
        when().
                post("/spartans").
        then().
                spec(validPostResponseSpec);


    }




}
