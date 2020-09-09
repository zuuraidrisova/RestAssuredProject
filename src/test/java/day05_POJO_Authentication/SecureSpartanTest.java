package day05_POJO_Authentication;

import static io.restassured.RestAssured. *;
import static org.hamcrest.Matchers.*;

import pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SecureSpartanTest {

    //authentication gives u identity : tells  who you are
    //authorization tells you what u can do
    //Authentication it is about who you are, and authorization is what you can
    // do according to who you are


    //we dont use our own ip in this one because our ip is with no auth
    //we use one of followings because they are with basic auth :
                                    // 54.160.106.84
                                     //100.24.235.129
                                    //23.23.75.140



    //add BeforeAll and use spartanApp with basic auth
   @BeforeAll
   public static void setUp(){

       baseURI = "http://54.160.106.84";
       port = 8000;
       basePath = "/api";


   }

    //add test and make simple get request /spartans/{id}

    //401 Unauthorized
    @DisplayName("testing get spartans/{id} endpoint with no credentials")
    @Test
    public void testGetSingleSpartanRequestSecured(){

       given().
               log().all().
               pathParam("id", 174).
       when().
               get("/spartans/{id}").
       then().
               log().all().
               statusCode(is(401));


    }


    @DisplayName("testing get spartans/{id} endpoint with credentials")
    @Test
    public void testGetSingleSpartanRequestWithCredentials(){

       int newID = createRandomSpartan();

       given().
               log().all().
               auth().basic("admin", "admin").
               pathParam("id",newID).
       when().
               get("/spartans/{id}").
       then().
               log().all().
               statusCode(is(200));


    }

    public static int createRandomSpartan(){

        Faker faker = new Faker();

        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000l, 9999999999l);

        //pojo
        Spartan spartan = new Spartan(name, gender, phone);

        Response response = given().
                                log().all().
                                auth().basic("admin", "admin").
                                 contentType(ContentType.JSON).
                                  body(spartan).
                            when().
                                  post("/spartans").
                                    prettyPeek();


       return response.path("data.id");

    }










}
