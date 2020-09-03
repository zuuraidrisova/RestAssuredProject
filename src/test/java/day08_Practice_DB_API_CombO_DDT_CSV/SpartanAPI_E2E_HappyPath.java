package day08_Practice_DB_API_CombO_DDT_CSV;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E_HappyPath {

    //we want id that generated  from post request accessible for all tests,
    // it must be static and at class level
    static int newID;

    //we can make our POJO at class level


    //Spartan crud operation happy path ==> crud is create read update and delete
    // all should pass so we say happy path

    //i want exactly this order => 1 Create, 2 Read, 3Update, 4 Delete

    @Order(1)
    @Test
    public void testCreateData(){

        //create one data here using  the POJO as body , do ur assertion
        //and grab the id so it can be used for all next 3 tests
        //you can make your post body from previous request at class level,
        //so it can be accessible everywhere
        //or u can also query the DB to get ur expected data
    }

    @Order(2)
    @Test
    public void testReadData(){

        //use the newID  that has been generated from post request
        //assert the response json according to expected data
        //expected data is the same data u used to create in previous request

    }

    @Order(3)
    @Test
    public void testUpdateData(){

    }

    @Order(4)
    @Test
    public void testDeleteData(){

    }


}
