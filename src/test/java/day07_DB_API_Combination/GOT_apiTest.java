package day07_DB_API_Combination;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GOT_apiTest {
    /*
    Make a request to GOT all characters endpoint :
GET https://api.got.show/api/book/characters
then store all the character name whose house value is House Stark
Hint :
The response is top level json array so you will not need to provide json path
The method will look like this getList(" findAll { condition here }.theFieldNameHere")
Assert the list size is 76
     */

    @DisplayName("getting all members of House Stark")
    @Test
    public void testGOT(){

        Response response =

        given().
                baseUri("https://api.got.show").
                basePath("/api/book").
        when().
                get("/characters");

        JsonPath jsonPath = response.jsonPath();

        List<String> houseStarkCharacters = jsonPath.getList("findAll{ it.house == 'House Stark' }.name");

        System.out.println("houseStarkCharacters = " + houseStarkCharacters);

        for(String each : houseStarkCharacters){

            System.out.println(each);

        }

        assertThat(houseStarkCharacters, hasSize(76));

        //check the list has item Eddard Stark
        assertThat(houseStarkCharacters, hasItem("Eddard Stark"));

        //check the list has items Robb Stark, Lyanna Stark, Arya Stark
        assertThat(houseStarkCharacters, hasItems("Robb Stark", "Lyanna Stark", "Arya Stark"));


    }





}