package day04PostRequestWithDifferentBodyTypes;

import pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PutAndPatch {

    @BeforeAll
    public static void setUp(){

        baseURI  = "http://35.153.51.63";
        port = 8000;
        basePath = "/api";

    }

    @DisplayName("testing put request method with map")
    @Test
    public void testPutRequestWithMap(){

        //put request to update spartan with id 210
        //name: put with map, gender :Male, phone; 7283519231
        //check status code is 204

        //how do i actually know that data is updated since put request does not have body?
         // we can make get request after put request and assert the body


        Map<String, Object> updatedBody = new LinkedHashMap<>();

        Faker faker = new Faker();

        String randomName = faker.name().firstName();

        updatedBody.put("name", randomName);
        updatedBody.put("gender", "Male");
        updatedBody.put("phone", 7283519231l);


        given().
                log().all().
                contentType(ContentType.JSON).
                body(updatedBody).// this is how we do it with the map

        when().
                put("/spartans/210").
                //put("/spartans/{id}", 210).

        then().
                log().all().
                statusCode(is(204));


        //  MAKING ANOTHER GET REQUEST TO MAKE SURE IT WORKED !!!!!

        when()
                .get("/spartans/{id}",210).
        then()
                .statusCode(200)
                .body("name" , is(randomName) )
        ;


    }





    @DisplayName("testing put request method with Pojo")
    @Test
    public void testPutRequestWithPojo(){

        //POJO is plain old java object


        //put request to update spartan with id 210
        //name: put with pojo, gender :Male, phone; 7283519231
        //check status code is 204

        //how do i actually know that data is updated since put request does not have body?
        // we can make get request after put request and assert the body


        Faker faker = new Faker();

        String randomName = faker.name().firstName();

        //this is how we can provide pojo instead

        Spartan spartan = new Spartan(randomName, "Male", 7283519231l);

        given().
                log().all().
                contentType(ContentType.JSON).
                body(spartan).
        when().
                put("/spartans/{id}", 210).

        then().
                log().all().
                statusCode(is(204));


        //  MAKING ANOTHER GET REQUEST TO MAKE SURE IT WORKED !!!!!

        when()
                .get("/spartans/{id}",210).
        then()
                .statusCode(200)
                .body("name" , is(randomName ) )
        ;

    }


    @DisplayName("PATCH request")
    @Test
    public void testPatchRequestPartialRequest(){

        //only update the name with faker

        String randomName = new Faker().name().firstName();
      //  String patchBody = "\"name\" : \""+randomName+ "\"";

        Map<String, Object> patchBodyMap = new HashMap<>();

        patchBodyMap.put("name" , randomName) ;


        given().
                log().all().
                contentType(ContentType.JSON).
                body(patchBodyMap).
        when().
                patch("/spartans/{id}", 210).
        then().
                log().all().
                statusCode(is(204));


        //add one qet request to make sure it updated just name partially

        when().
                get("/spartans/{id}", 210).
        then().
                statusCode(200).
                body("name" , is(randomName ) );




    }


}
