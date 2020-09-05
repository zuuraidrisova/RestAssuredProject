package day11_HowToHandleDynamicData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan2;
import utility.ConfigurationReader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GettingRandomNumber {


    @Test
    public void testRandom(){

        //Random class comes from Java.util package and can provide some random numbers
        // in different data type
        // first we need to create an object of the class
        Random random = new Random();

       int randomNumber =  random.nextInt(10); //it will give u between 0 to 10

        System.out.println("randomNumber = " + randomNumber);

        List<String> names = Arrays.asList("Anna", "Vincent", "Zuleyha","Emrah", "Natalya",
        "Zuura","Namik", "Hilal","Nurgul", "Isa");

        //we want to get random name from list each time

        System.out.println("Lucky name = " + names.get(randomNumber));


    }

}
