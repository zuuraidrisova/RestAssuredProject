package day02_Parameters;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class ZipCodeAPI {


    @BeforeAll
    public static void setUp(){

        baseURI = "http://api.zippopotam.us";
        basePath = "/us";

    }
    /*
     Please open up new class called ZipCode Test
	    Add baseURI as api.zippopotam.us
	    	basePath as /us/
	    under @BeforeAll

	    add
	    	path variable {zipcode} in given section
	    send Get request
	    then  check the status code 200
	    check the contentype header is json
	         body : post code -- the zipcode you entered
	         		country  United States
	         	    longitude  -- the expected value
	         	    state    --  the expected value
     */

    @DisplayName("testing zip to city")
    @Test
    public void testZipCode(){

        given().
                log().all().
                pathParam("zipCode", 44123).
        when().
                get("/{zipCode}").
        then().
                log().all().
                statusCode(is(200)).
                contentType(ContentType.JSON).
                body(" 'post code' ", is("44123")).
                body("country", equalTo("United States")).
                body("places[0].longitude", is("-81.5258")).
                body("places[0].state", equalTo("Ohio"));


    }


    @DisplayName("testing city to zip")
    @Test
    public void testCityToZip(){


        given().
                log().all().
                pathParam("state","VA").
                pathParam("city", "Fairfax").
         when().
                get("/{state}/{city}").
               // get("/{state}/{city}", "VA","Fairfax"). if u want one shot
        then().
                log().all().
                statusCode(is(200)).
                body(" 'country abbreviation' ", is("US")).
                body("places[0].latitude", equalTo("38.8458")).
                body("places[-1].latitude", equalTo("38.7602"));




    }
}
