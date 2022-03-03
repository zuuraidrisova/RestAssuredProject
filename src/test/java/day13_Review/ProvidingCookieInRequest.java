package day13_Review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
public class ProvidingCookieInRequest {

      /*
Provide cookie  with this request : https://postman-echo.com/cookies
 Try to add 2 cookies in the requests by following the doc.
     */

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://postman-echo.com";

    }

    @Test
    public void testHowToProvideCookies(){

        given().
                log().all().
                contentType(ContentType.JSON).
                cookie("B18", "Awesome").
                cookie("Moto", "HakunaMatata").
         when().
                get("/cookies").
                prettyPeek().
         then().
                statusCode(200);

    }

    @AfterAll
    public static void tearDown(){

        RestAssured.reset();

    }


}
