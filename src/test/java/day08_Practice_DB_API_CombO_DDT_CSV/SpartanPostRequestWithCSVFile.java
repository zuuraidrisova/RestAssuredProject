package day08_Practice_DB_API_CombO_DDT_CSV;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pojo.Spartan;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanPostRequestWithCSVFile {

    /*

----- Data Drive your POST /api/Spartans request
----  Create a csv file called allSpartans.csv under src/test/resources folder
            add 3 column name , gender , phone
            add 6 rows of valid data
            then try to send post request using these data

     */

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }


    @ParameterizedTest(name = "iteration {index} | name : {0}, gender : {1}, phone : {2}")
    @CsvFileSource(resources = "/allSpartans.csv", numLinesToSkip = 1)
    public void addSpartansWithCSVFile(String name, String gender, long phone){


        Spartan spartan = new Spartan(name, gender, phone);

        given().
                log().all().
                contentType(ContentType.JSON).
                body(spartan).
        when().
                log().all().
                post("/spartans").
        then().
                statusCode(is(201));



    }


}
