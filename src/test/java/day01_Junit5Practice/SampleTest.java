package day01_Junit5Practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    public void calculatorTest(){

        System.out.println("Hello World");

        System.out.println(12+24);

        assertTrue(true);

        //assert 4+5 is 9
        assertEquals(9, 6+3);

        //how do we add error message if assertion fails
        assertEquals(9, 6+3, "Hey, wrong result!");



    }

    //u can add the display name for ur test instead of method name

    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){

        //write a simple assertion
        //concatenate ur first name and last name
        //and make assertion its equal to ur full name

        String fullName = "Zuura Idrisova";

        assertEquals("Zuura ".concat("Idrisova"), fullName);


    }

}
