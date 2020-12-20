package day09_DataDrivenTestingCSV;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pojo.Spartan;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostSpartanDataDrivenWIthCSV {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartans2.csv", numLinesToSkip = 1)
    public void testPostRequestDDT_CSV(String csvName, String csvGender, long csvPhone){


        // i need the post body as pojo

        Spartan spartan = new Spartan(csvName, csvGender, csvPhone);


        given().
                contentType(ContentType.JSON).
                body(spartan).
        when().
                post("/spartans").
        then().
                statusCode(is(201)).
                body("success", is("A Spartan is Born!")).
                body("data.name", is(csvName)).
                body("data.gender", is(csvGender)).
                body("data.phone", is(csvPhone)).
                body("data.id", is(notNullValue()));

    }


    @AfterAll
    public static void tearDown(){

        RestAssured.reset();
    }
}
