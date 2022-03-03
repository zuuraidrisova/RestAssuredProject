package day07_DB_API_Combination;

import org.junit.jupiter.api.Test;

public class WhatIsVarArgs {

    //variable number of arguments
    //create a method that accept N numbers and add all of them
    //and print result

    public static void addAllNumbers(int [] nums){

        int sum = 0;

        for(int eachNum :  nums){

            sum += eachNum;

        }

        System.out.println("sum = "+sum);
    }

    //VARARGS it is what 3 dots ... for which you can use to pass as many values
    // as you want and it will store them to array
    // you can also write with ... instead of []

    // this method parameter int... nums means
    // when you call the method , it can accept any number of arguments
    // THIS IS THE ONLY PLACE YOU CAN USE ...
    // ANYWHERE ELSE Other THAN METHOD PARAM IT WILL NOT WORK !!!!

    public static void addAllNumbersVarArgs(int ... nums){

        int sum = 0;

        for(int eachNum :  nums){

            sum += eachNum;

        }

        System.out.println("sum = " + sum);

    }


    @Test
    public void testAdd(){

        addAllNumbers(new int[] {1,2,3,4,5,6});

        addAllNumbersVarArgs(1,2,3,4,5,6);

    }



}
