package day13_Review;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Test_OAUTH2ExampleInRestAssured {

    //   Request to get the token
    //https://cybertek-reservation-api-qa.herokuapp.com/sign?email=jalabaster7f@drupal.org&password=terimapam

    //request to get all rooms by authorizing it with token
    //https://cybertek-reservation-api-qa.herokuapp.com/api/rooms


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }


    @DisplayName("Getting the token and making an authorized request")
    @Test
    public void test(){


        Response response =

        given().
                queryParam("email", "jalabaster7f@drupal.org").
                queryParam("password", "terimapam").
        when().
                get("/sign");



        JsonPath jsonPath = response.jsonPath();


        String token = jsonPath.getString("accessToken");


        System.out.println("token = " + token);

        //send get request to get rooms by adding oauth2

        given().
                auth().oauth2(token). //can be done as below too
               // header("Authorization", "Bearer "+token).
        when().
                get("/api/rooms").prettyPeek();


    }


}
