package day07_DB_API_Combination;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import utility.LibraryAPI_Utility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_API_DB_test {

    private static String libraryToken;

    @BeforeAll
    public static void setUp(){

        String active_env = ConfigurationReader.getProperty("active_env");

        //TODO add a utility class that contains below method
        libraryToken = LibraryAPI_Utility.setUpRestAssureAndDB_forEnv(active_env);

    }


    @Test
    public void testToken(){

        System.out.println("libraryToken = " + libraryToken);

        //we will make a call  to  /dashboard_stats endpoint and check the data against database data

        DB_Utility.runQuery("select count(*) from books");//it returns book count as a single row and column

        String bookCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("bookCount = " + bookCount);

        System.out.println("=====================================");

        DB_Utility.runQuery("select count(*) from users");

        String userCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("userCount = " + userCount);

        System.out.println("=====================================");

        DB_Utility.runQuery("select count(*) from book_borrow where is_returned = false");

        String borrowedBookCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("borrowedBookCount = " + borrowedBookCount);

        given().
                log().all().
                header("x-library-token", libraryToken).
        when().
                get("/dashboard_stats").
        then().
                body("book_count", is(bookCount)).
                body("users", is(userCount)).
                body("borrowed_books", is(borrowedBookCount));


    }

    @AfterAll
    public static void destroy(){

        DB_Utility.destroy();

        RestAssured.reset(); //this is for resetting all the values  we set for RestAssured itself

    }




}
