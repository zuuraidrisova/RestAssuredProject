package day11_HowToHandleDynamicData;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/*
***If the data change often and you have access to DB
	Query the database to get up to date data and feed the data to your test.
 */

public class GettingTestDataFromSpartanDB {

    @BeforeAll
    public static void init(){

       //RestAssured.baseURI = "http://35.153.51.63/";
       //RestAssured.port = 8000;
        baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

        DB_Utility.createConnection(ConfigurationReader.getProperty("active_env"));


    }


    @DisplayName("Testing get /spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB(){

        //i want to write a query the latest data created from DB

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;

        DB_Utility.runQuery(myQuery);

        Map<String, String> firstRowMap = DB_Utility.getRowMap(1);

        System.out.println("firstRowMap = " + firstRowMap);

        //get the id, name, gender, phone out of this map

         int idFromDB = Integer.parseInt(firstRowMap.get("SPARTAN_ID"));

        System.out.println("id = " + idFromDB);

        String nameFromDB = firstRowMap.get("NAME");

        System.out.println("nameFromDB = " + nameFromDB);

        String genderFromDB = firstRowMap.get("GENDER");

        System.out.println("genderFromDB = " + genderFromDB);

        long phoneFromDB = Long.parseLong(firstRowMap.get("PHONE"));

        System.out.println("phoneFromDB = " + phoneFromDB);

        //API starts below

        when().
                get("/spartans/{id}", idFromDB).
        then().
                statusCode(is(200)).
                body("id", equalTo(idFromDB)).
                body("name", is(nameFromDB)).
                body("gender", is(genderFromDB)).
                body("phone", equalTo(phoneFromDB)).
                contentType(ContentType.JSON).
                log().all();

        // the test is failing if the phone number fall within the range of int
        // because body method is just getting it as int
        // and we can not compare int with long since they are not same data type
        // can I just convert
        // my phone value from response to long directly inside jsonPath
        // YES WE CAN ! Because jsonPath is groovy , we can call groovy function
        // WAIT !! I DO NOT KNOW THE METHOD!!! --->> GOOGLE!!
        // I found If I add toLong() to the int value , it's turning it into long value
        // now we will try it out.

            //  .body("phone.toLong()", is(phoneFromDB) ) ;

    }


    @DisplayName("Testing get /spartans/{id} by getting the id from DB randomly")
    @Test
    public void testDataFromDB_randomly(){


        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;

        DB_Utility.runQuery(myQuery);

        // we want to get the rowNum for below method randomly from 1 till the last row count
        // so I can always get different data for my test
        // so first I need to get the row count so I can set the max of my random number

        int rowCount = DB_Utility.getRowCount();

        //get a random number from 1 to rowCount value
        int randomRowNum = new Faker().number().numberBetween(1,rowCount);

        Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);


        System.out.println("randomRowNum = " + randomRowNum);

        System.out.println("randomRowMap = " + randomRowMap);


    }



}
