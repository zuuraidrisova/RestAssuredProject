package day05_POJO_Authentication;

import pojo.Spartan2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class jsonToJavaPojo {


    //json to java object
    //store the result of get spartans/{id} into pojo object
    /*
    {
    "id": 253,
    "name": "Mariette",
    "gender": "Female",
    "phone": 8339519234
        }
     */

    //since our existing spartan object does not have id field, so we cant save id field


    @BeforeAll
    public static void setUp(){

        baseURI = "http://54.160.106.84";
        port = 8000;
        basePath = "/api";

    }

    @DisplayName("json to pojo get spartan/{id}")
    @Test
    public void testSpartanJsonToSpartanObject(){

        int newID = SecureSpartanTest.createRandomSpartan();

        Response response =

        given().
                log().all().
                auth().basic("admin","admin").
                pathParam("id", newID).
        when().
                get("/spartans/{id}").
                prettyPeek();


        //as() method from response accepts a class type to define what type of object
        // u r trying to  store the response as
        //Spartan2 class we created has all the fields that match the json fields from response
        //so it will map all the json fields to the java fields and return Spartan2 POJO object

       // in a simple word turn below json into Java object
        /**
         * {
         *     "id": 261,
         *     "name": "Elma",
         *     "gender": "Male",
         *     "phone": 9999999998
         * }
         *
         * into  new Spartan2(261,"Elma","Male",9999999998L) Java Object
         * so we can work with the data using java object directly
         */

        Spartan2 spartan2 = response.as(Spartan2.class);

        //above line is almost as if u r doing below line
       // Spartan2 spartan2 = new Spartan2(261, "Elma", "Male", 72316497812l);

        System.out.println("spartan2 = " + spartan2);


    }


    @DisplayName("search request and save 1st result as Spartan2 pojo")
    @Test
    public void gettingNestedJsonAsPojo(){

        Response response =

        given().
                log().all().
                auth().basic("admin", "admin").
                queryParam("gender","Male").
        when().
                get("/spartans/search");
                //prettyPeek();


        System.out.println("response.statusCode() = " + response.statusCode());

        //print out the first  id and name from response

        JsonPath jp =  response.jsonPath();

        System.out.println("first guy  id = " + jp.getInt("content[0].id"));
        System.out.println("first guy name = " + jp.getString("content[0].name"));

        //lets save the entire first json object in the array into Spartan2 pojo
       Spartan2 spartan2 =  jp.getObject("content[0]", Spartan2.class);

        System.out.println("spartan2 = " + spartan2);

        System.out.println("spartan2.getName() = " + spartan2.getName());
        System.out.println("spartan2.getGender() = " + spartan2.getGender());
        System.out.println("spartan2.getPhone() = " + spartan2.getPhone());


    }

    //how can i store the entire jsonArray into the List<Spartan2> ?

    @DisplayName("Save the json array as list<Spartan2>")
    @Test
    public void testJsonArrayToListOfPojo(){

        Response response =

                given().
                        queryParam("gender", "Female").
                        auth().basic("admin", "admin").
                when().
                        get("/spartans/search");

        //store all id as list of Integers

        JsonPath jsonPath = response.jsonPath();

        List<Integer> ids = jsonPath.getList("content.id");

        System.out.println("ids = " + ids);

        //store all names as list of String

        List<Integer> names = new ArrayList<>();

        names.addAll(jsonPath.getList("content.name"));

        System.out.println("names = " + names);


        //storing the entire jsonArray as list of Spartan2

        List<Spartan2> entireJsonArray = jsonPath.getList("content");

        System.out.println("entireJsonArray = " + entireJsonArray);


        //or we can store the entire jsonArray as list of Spartan2 this way

        List<Spartan2> spartan2List = jsonPath.getList("content", Spartan2.class);

        System.out.println("spartan2List = " + spartan2List);

        for(Spartan2 each : spartan2List){

            System.out.println(each);
        }

        // this is optional lambda way of for each
        spartan2List.forEach( each -> System.out.println(each));


    }






}
