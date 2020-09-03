package day08_Practice_DB_API_CombO_DDT_CSV;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderInJunit5 {

    // JUNIT5   default execution order is alpha-numericalOrder
    //what if we need to specify order of execution for my tests instead of default one
    // then i need below annotation at class level , on top of class name
    // @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    //then i need to put  @Order(1) annotation to each test method level


    @Order(1)
    @Test
    public void testZ(){

    }

    @Order(2)
    @Test
    public void testA(){

    }

    @Order(3)
    @Test
    public void testB(){

    }


    @Order(4)
    @Test
    public void testC(){

    }


}
