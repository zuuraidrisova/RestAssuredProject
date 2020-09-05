package day04_PostRequestWithDifferentBodyTypes;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestTest {


    @BeforeAll
    public static void setUp(){

        baseURI = "http://35.153.51.63";
        port = 8000 ;
        basePath = "/api";

    }


    @DisplayName("post request with String as body")
    @Test
    public void testPostWithStringBody() {

        Faker faker = new Faker();

        String randomName = faker.name().firstName();

        System.out.println("randomName = " + randomName);

        String bodyString = "{\n" +
                "\"name\"  : \"" + randomName+ "\",\n" +

                "\"gender\": \"Female\",\n" +

                "\"phone\": 6234567890\n" +

                "}";


        given().
                log().all().
                contentType(ContentType.JSON).
                body(bodyString).
        when().
                post("/spartans").
         then().
                log().all().
                statusCode(is(201)).
                body("data.name", equalTo(randomName)) ;


    }



    @DisplayName("posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        //create a file object that point to spartan.json u just added
        //so we can use this as body in post request

        File file1 = new File("spartan.json");

        given().
                log().all().
                contentType(ContentType.JSON).
                body(file1).
        when().
                post("/spartans").
        then().
                log().all().
                statusCode(is(201)).
                body("data.name", is("From File"));

    }


    @DisplayName("posting with map object as body")
    @Test
    public void testPostWithMapAsBody(){


        //add dependency jackson-databind

        // create a Map<String, Object> as hashMap
        //add name, gender, phone


        Map<String, Object>  bodyMap = new HashMap<>();
        //is we want to keep insertion order of map objects we can use LinkedHashMap

        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone",8327146121l);

        System.out.println("bodyMap = " + bodyMap);

        given().
                log().all().
                contentType(ContentType.JSON).
                body(bodyMap).//jackson-data-bind turn ur java map object to json here
        when().
                post("/spartans").
        then().
                log().all().
                statusCode(equalTo(201)).
                body("data.name", is(bodyMap.get("name")));


    }




}
