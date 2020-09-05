package day03_GetRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestExample {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }

    @DisplayName("testing the POST endpoint in spartan api")
    @Test
    public void testAddSpartan(){

        String myBodyData = " {\n" +
                "            \"name\": \"Aika\",\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"phone\": 2189634842\n" +
                "        }";

        System.out.println("myBodyData = " + myBodyData);


        given().
                contentType(ContentType.JSON).
                body(myBodyData).
                log().all().
        when().
                post("/spartans").
        then().
                log().all().
                statusCode(is(201)).
                contentType(ContentType.JSON).
                body("success", is("A Spartan is Born!")).
                body("data.id", equalTo(195)).
                body("data.name", equalTo("Aika"));
                //body("data.phone", is("2189634842"));





    }



    //please create another test and make a post request, store the response object
    //save the id into int variable
    //save the name to String var
    //as hw save the spartan data field into map

    @DisplayName("Practice extracting data")
    @Test
    public void testPostRequestExtractingData(){

        String myBodyData = " {\n" +
                "            \"name\": \"Aya\",\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"phone\": 2189634942\n" +
                "        }";

        Response response =


        given().
                contentType(ContentType.JSON).
                body(myBodyData).
                log().all().
         when().
                post("/spartans").
                prettyPeek();


        System.out.println("id using path = " + response.path("data.id"));
        System.out.println("name using path  = " + response.path("data.name"));


       JsonPath jsonPath =  response.jsonPath();

         int id =  jsonPath.getInt("data.id");

        System.out.println("id using json path = " + id);

        String name = jsonPath.getString("data.name");

        System.out.println("name using json path  = " + name);

    }


}
