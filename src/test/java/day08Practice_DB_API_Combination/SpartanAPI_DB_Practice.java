package day08Practice_DB_API_Combination;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanAPI_DB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     */

    @BeforeAll
    public static void setUp(){

        //35.153.51.63
        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

       DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
               ConfigurationReader.getProperty("spartan1.database.username"),
               ConfigurationReader.getProperty("spartan1.database.password"));

    }

    @DisplayName("Testing out my DB Connection")
    @Test
    public void testDB(){

       // DB_Utility.runQuery("select * from spartans");
        //DB_Utility.displayAllData();

        //run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);
        DB_Utility.displayAllData();

        int expectedResult = DB_Utility.getRowCount();

        System.out.println("expectedResult = " + expectedResult);

    }

    @DisplayName("Testing spartans/search endpoint and validate against DB")
    @Test
    public void testSearchEndpointAPI(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response =

        given().
                log().all().
                queryParam("gender", "Female").
                queryParam("nameContains", "a").
        when().
                get("/spartans/search").
                prettyPeek();


        int actualNumberOfElements = response.jsonPath().getInt("numberOfElements");

        System.out.println("actual numberOfElements = " + actualNumberOfElements);


        //run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);

        int expectedResult = DB_Utility.getRowCount();

        System.out.println("expectedResult = " + expectedResult);

        //this is using junit assertion
        Assertions.assertEquals(actualNumberOfElements, expectedResult);
    }

    @DisplayName("testing /spartans/search endpoint and validate againt DB for all IDs")
    @Test
    public void testSearchVerifyAllIDs(){

        //first api part
        Response  response =

                given().
                        log().all().
                        queryParam("gender", "Female").
                        queryParam("nameContains", "a").
                when().
                        get("/spartans/search").
                        prettyPeek();


        // We want to store the id list as List<String> rather than List of Integer so we can compare easily
        // with the List<String> we got from DB Response , and no parsing needed
        // so we used the getList method that accept 2 parameters
        // the jsonpath to get the list and the Data type of the List you want ! -->> String.class

        List<String> idListFromResponse = response.jsonPath().getList("content.id",String.class );

        //here DB part starts

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);

        List<String> idListFromDB = DB_Utility.getColumnDataAsList(1);

        assertThat(idListFromResponse.size(), equalTo(idListFromDB.size()));



    }




    @AfterAll
    public static  void tearDown(){

        RestAssured.reset();
        DB_Utility.destroy();

    }

}
