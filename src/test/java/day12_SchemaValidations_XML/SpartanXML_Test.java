package day12_SchemaValidations_XML;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanXML_Test {

    /*
    Practice XML Response using GET /Spartans
Create a class called SpartanXML_Test
Add @BeforeAll method to set up your baseURI and basePath
Create a Test and send GET /Spartans by specifying accept header as xml.
Verify you get xml as response content type.
Verify the first spartan name, id , gender .
     */

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";

    }

    @DisplayName("Test XML Response from get /spartans endpoint")
    @Test
    public void testSpartansXML(){


        given().
                accept(ContentType.XML).
        when().
                get("/spartans").
        then().
                //log().all().
                statusCode(is(200)).
                contentType(ContentType.XML).
                body("List.item[0].id", is("104")).
                body("List.item[0].name", is("Swagger")).
                body("List.item[0].gender", is("Male"));



    }


    @DisplayName("XML Path object to extract data")
    @Test
    public void testXMLPath(){

        Response response =

        given().
                accept(ContentType.XML).
        when().
                get("/spartans");

        XmlPath xmlPath = response.xmlPath();


       int firstSpartanID =  xmlPath.getInt("List.item[0].id");

        System.out.println("firstSpartanID = " + firstSpartanID);

        String firstSpartanName = xmlPath.getString("List.item[0].name");

        System.out.println("firstSpartanName = " + firstSpartanName);

        String firstSpartanGender = xmlPath.getString("List.item[0].gender");

        System.out.println("firstSpartanGender = " + firstSpartanGender);

        long firstSpartanPhone = xmlPath.getLong("List.item[0].phone");

        System.out.println("firstSpartanPhone = " + firstSpartanPhone);

        //get all ids and store into the list

        List<Integer> listOfIds = xmlPath.getList("List.item.id", Integer.class);

        System.out.println("listOfIds = " + listOfIds);


        assertThat(listOfIds, hasSize(231));

        assertThat(listOfIds, hasItems(19, 104, 238));


        // Get a List of Long from the phone numbers
        // first check the size is 231
        // check it has few phone numbers you specified

        List<Long> listOfPhones = xmlPath.getList("List.item.phone", Long.class);

        System.out.println("listOfPhones = " + listOfPhones);

        assertThat(listOfPhones, hasSize(231));

        assertThat(listOfPhones, hasItems(28396173292l,7685940321l,3456789021l));

        // optionally --
        // check every item is greaterThan 1000000000

         assertThat(listOfPhones, everyItem(greaterThan(1000000000l)));


        // Get a List of String from the names
        // find out how many unique names you have

        List<String> listOfNames = xmlPath.getList("List.item.name");

        System.out.println("listOfNames = " + listOfNames);

       //assertThat(listOfNames, everyItem(equalTo(1)));

        Set<String> listOfUniqueNames = new LinkedHashSet<>();

        listOfUniqueNames.addAll(listOfNames);

        //Set interface defines the collection of unique elements
        //creating a LinkedHashSet by copying everything from list will auto-remove duplicates
       // Set<String> listOfUniqueNames = new LinkedHashSet<>(listOfNames); ==> can go into the constructor right away

        System.out.println("listOfUniqueNames = " + listOfUniqueNames);

        System.out.println("listOfUniqueNames.size() = " + listOfUniqueNames.size());

        System.out.println("listOfNames.size() = " + listOfNames.size());
    }




}
