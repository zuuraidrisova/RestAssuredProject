package day04_PostRequestWithDifferentBodyTypes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class getOneSpartanMethod {

    // CREATE A METHOD THAT POST A RANDOM SPARTAN TO THE SERVER
    // AND RETURN THE ID OF THAT SPARTAN , SO YOU CAN ALWAYS USE A DATA THAT EXISTS


    public static int postRandomSpartan(String name, String gender,  long phone){

        Map<String, Object> dataMap = new LinkedHashMap<>();

        dataMap.put("name", name);
        dataMap.put("gender", gender);
        dataMap.put("phone", phone);

        RestAssured.baseURI = "http://54.236.150.168";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        Response responseJson =

                         given().
                                 contentType(ContentType.JSON).
                                 body(dataMap).
                          when().
                                 post("/spartans");

        JsonPath jsonPath = responseJson.jsonPath();

        return  jsonPath.getInt("data.id");


    }


}
