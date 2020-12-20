package day01_Junit5Practice;

//In order to use REST assured effectively it's recommended to statically import
// methods from the following classes:

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FirstRequest {


    // you may use your own IP
    // we are using spartan app that does not require password
    //http://54.174.216.245:8000/api/hello

    @Test
    public void test1(){

        //RestAssured.get("URL HERE")
        // SINCE WE DID THE STATIC IMPORT
        // we can directly call the get method
        // after we send the request
        // we can save the result in to a type called Response
        // need this  import io.restassured.response.Response;
        Response response = get("http://54.174.216.245:8000/api/hello") ;

        //Response object stores all info about response we got like: status code, body, header
        //getting status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //this is another way of getting status code starting with HTTP/ 1.1
        System.out.println("response.statusLine() = " + response.statusLine());

        //getting header info of Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //getting content type
        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        //getting content length header
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));


        assertEquals(200, response.statusCode() );

        assertEquals("17", response.header("Content-Length"));

        assertTrue(response.header("Content-Type").contains("text/plain"));

        System.out.println("Success");



    }

    @DisplayName("Testing hello endpoint")
    @Test
    public void testHello(){

        Response response = get("http://54.174.216.245:8000/api/hello") ;

        //testing the status code returns as expected
        assertEquals(200, response.getStatusCode());

        //testing content type  header value is:  "text/plain"
        assertTrue(response.getContentType().contains("text/plain"));

        //testing content-length is 17
        assertEquals("17", response.header("Content-Length"));

        System.out.println("Success");

    }


    @DisplayName("Testing hello endpoint body")
    @Test
    public void testingHelloBody(){

        Response response = get("http://54.174.216.245:8000/api/hello");

        //getting the body by calling body method
        System.out.println("response.getBody() = " + response.getBody().asString());

        //getting body as String using asString method of response object
        String body = response.asString();
        System.out.println("body = " + body);

        //assert the body is Hello from Sparta

        String expectedBody = "Hello from Sparta";
        String actualBody =  response.asString();
        assertEquals(expectedBody, actualBody);

        // asserting  length is 17
        assertEquals(17, actualBody.length());

        System.out.println("Success");
    }


    @DisplayName("Printing response body using method")
    @Test
    public void printBody(){

        Response response = get("http://54.174.216.245:8000/api/hello");

        //easy way to print the response body and return at the same time
        //return type is String, prints only body
        String body = response.prettyPrint();

        int expectedLengthBody = 17;
        int actualLengthBody = body.length();

        assertEquals(expectedLengthBody, actualLengthBody);

        System.out.println("SUCCESS");

        System.out.println("=================================");

        //prettyPeek() is another way of seeing the body quick
        //it prints the entire response
        //return type is Response, and it will return status code, header and body
        response.prettyPeek();

        System.out.println("=================================");

        //i want to see entire response and save status code into a variable in same statement

        int statusCode = response.prettyPeek().getStatusCode();

        System.out.println("statusCode = " + statusCode);


    }




}
