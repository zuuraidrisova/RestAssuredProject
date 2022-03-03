package day07_DB_API_Combination;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

public class MySQL_Test {

    //add ur dependency for mysql driver
    //add ur db_utility class
    //add ur Configuration.Reader
    //DB_Utility class has createConnection method and it accepts env as string and add
    //.database at the end to find correct credentials
    // ex: if we add library1, it will pick up below prmtrs:
    // library1.database.url , library1.database.username,  library1.database.password


    @BeforeAll
    public static void setUp(){

        DB_Utility.createConnection("library1");

    }

    @DisplayName("test things out to check whether DB_Utility is working properly")
    @Test
    public void testDB_Utility(){

        DB_Utility.runQuery("select * from books");

        DB_Utility.displayAllData();


    }


    @AfterAll
    public static void tearDown(){

        DB_Utility.destroy();

    }



}
