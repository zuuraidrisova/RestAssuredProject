package day02_Parameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;

public class BreakingBarAPI {


    @DisplayName("Get all characters from BreakingBad")
    @Test
    public void testBreakingBad() {

        //https://www.breakingbadapi.com/api/characters

        when().
                get("https://www.breakingbadapi.com/api/characters").
                prettyPeek().
        then().
                statusCode(is(200)).
                header("Content-Type", is("application/json; charset=utf-8"));
              //contentType(is("application/json; charset=utf-8"));


    }






}
