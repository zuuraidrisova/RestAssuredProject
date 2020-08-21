package day01_Junit5Practice;

import org.junit.jupiter.api.*;

public class BeforeAfterJunit5 {

   //beforeAll method run only once before entire test same as @BeforeClass
    //these are junit5 specific annotations
   @BeforeAll
   public static  void setUp(){

        System.out.println("This runs before all!");
    }


    @BeforeEach
    public void beforeEachTest(){

        System.out.println("Running before each tests!");
    }


    @Test
    public void test1(){

        System.out.println("Test 1 is running!");
    }

    @Test
    public void test2(){

        System.out.println("Test 2 is running!");
    }

    @AfterEach
    public void afterEachTest(){

        System.out.println("Running after each tests!");
    }


    //this method  will run only after all tests same as @AfterClass
    @AfterAll
    public static void tearDown(){

        System.out.println("This runs all the way at the end!");
    }
}
