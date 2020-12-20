package utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryAPI_Utility {

    public static String setUpRestAssureAndDB_forEnv(String env){

        baseURI = ConfigurationReader.getProperty(env + ".base_url");
        basePath = "/rest/v1";

        DB_Utility.createConnection(env);

        // We want to return this token out of the method so the next class can use it

        return LibraryAPI_Utility.loginAndGetToken(ConfigurationReader.getProperty(env + ".librarian_username")
                , ConfigurationReader.getProperty(env + ".librarian_password"));


    }


    //we want to keep the method which gets us token here
    //so we do not have to copy paste anymore

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password){

        Response jsonResponse  =
                given().

                contentType(ContentType.URLENC).
                formParam("email", username ).
                formParam("password", password).

                 when().

                post("/login");

        JsonPath jsonPath = jsonResponse.jsonPath();

        String token = jsonPath.getString("token");

        return  token;


    }

}
