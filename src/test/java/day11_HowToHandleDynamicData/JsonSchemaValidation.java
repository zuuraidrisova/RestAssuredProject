package day11_HowToHandleDynamicData;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {

    //create a file called singleSpartanSchema.json
    //under src/test.resources
    //add the schema content below
    /*
    {
  "definitions": {},
  "$schema": "http://json-schema.org/draft-07/schema#",
  "$id": "https://example.com/object1590716273.json",
  "title": "Root",
  "type": "object",
  "required": [
    "id",
    "name",
    "gender",
    "phone"
  ],
  "properties": {
    "id": {
      "$id": "#root/id",
      "title": "Id",
      "type": "integer",
      "examples": [
        33
      ],
      "default": 0
    },
    "name": {
      "$id": "#root/name",
      "title": "Name",
      "type": "string",
      "default": "",
      "examples": [
        "Wilek"
      ],
      "pattern": "^.*$"
    },
    "gender": {
      "$id": "#root/gender",
      "title": "Gender",
      "type": "string",
      "default": "",
      "examples": [
        "Male"
      ],
      "pattern": "(Male|Female)"
    },
    "phone": {
      "$id": "#root/phone",
      "title": "Phone",
      "type": "integer",
      "examples": [
        2877865902
      ],
      "default": 0
    }
  }
}
     */

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";


    }


    @DisplayName("testing json format ==> json schema validation")
    @Test
    public void testJsonSchemaValidation(){


        given().
                log().uri().
        when().
                get("/spartans/{id}", 3).
                prettyPeek().
        then().
                body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"));


    }


}
