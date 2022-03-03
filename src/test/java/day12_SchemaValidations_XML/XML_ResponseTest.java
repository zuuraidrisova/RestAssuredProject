package day12_SchemaValidations_XML;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class XML_ResponseTest {

    //XML is EXTENSIBLE MARKUP LANGUAGE

    @BeforeAll
    public static void setUp(){

        //we are going to send a get request to this endpoint
        //in this Rest API request, it uses query param to specify response content type

        //https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml

        RestAssured.baseURI = "https://vpic.nhtsa.dot.gov";
        RestAssured.basePath = "/api/vehicles";


    }


    @Test
    public void testXML(){

        given().
                log().all().
                queryParam("format", "xml").
        when().
                get("/GetMakeForManufacturer/988").
        then().
                log().all().
                contentType(ContentType.XML).
                statusCode(is(200)).
                //the path must match, and value is always String in xml
                body("Response.Count", is("2")).
                body("Response.Message", is("Results returned successfully")).
                // find out the first Make_ID under the result by providing the index in the path
                body("Response.Results.MakesForMfg[0].Make_ID", is("474")).
               // check the Make_Name in second result is Acura
                body("Response.Results.MakesForMfg[1].Make_Name", is("ACURA"));


    }


    @AfterAll
    public static void tearDown(){

        RestAssured.reset();

    }




}
