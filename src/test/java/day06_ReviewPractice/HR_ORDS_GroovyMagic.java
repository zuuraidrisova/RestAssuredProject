package day06_ReviewPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_GroovyMagic {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://35.153.51.63";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";

    }


    @DisplayName("Testing the  /employees endpoint")
    @Test
    public void testEmployees(){

        Response response =

                        given().
                                get("/employees");
                                //prettyPeek();


        JsonPath jsonPath =  response.jsonPath();


        List<Integer> listID = jsonPath.getList("items.employee_id");

        System.out.println("listID = " + listID);

        for (Integer each : listID){

            System.out.println(each);
        }

        System.out.println("===========================================");

        System.out.println("first employee_id = " + listID.get(0));

        System.out.println("last employee_id = " + listID.get(listID.size()-1));

        System.out.println("===========================================");


        for(int i = 0; i < 5; i++){//from first id till fifth id

            System.out.println(listID.get(i));
        }

        System.out.println("===========================================");

        //Groovy magic starts below :

        System.out.println("from first id till fifth id = " + jsonPath.getList("items[0..4].employee_id"));

        System.out.println("===========================================");

        //get all last_names from 10-15

        System.out.println("Last names from 10 to 15 index : "+
                jsonPath.getList("items[10..15].last_name"));

        //get the employee last_name with employee id of 105
        //find and find all where you specify the criteria to restrict the result
        //find method will return single value that fall into criteria compared to findAll will return a list
        //find { it.employee_id == 105 }
        //<it> means ==> each item in ur json array

        System.out.println("===========================================");

      String result = jsonPath.getString("items.find{ it.employee_id == 105}.last_name");

        System.out.println("result = " + result);

        System.out.println("===========================================");

        // using above example : find the salary of employee with email value LDEHAAN
       int salary = jsonPath.getInt("items.find{ it.email == \"LDEHAAN\"}.salary");

        System.out.println("salary = " + salary);

        System.out.println("===========================================");

        //findAll will get all result that match the criteria and return a list
        //save all the first_name of employees with salary more that 15000

        List<String> richPeopleNames = jsonPath.getList("items.findAll{ it.salary > 15000 }.first_name");

        System.out.println("richPeopleNames = " + richPeopleNames);

        System.out.println("===========================================");

        //find all the phone_number in department_id == 90

        List<String> phoneNumberOfDepartment90 = jsonPath.getList("items.findAll{it.department_id == 90}.phone_number");

        System.out.println("phoneNumberOfDepartment90 = " + phoneNumberOfDepartment90);


        System.out.println("===========================================");

        int maxSalary = jsonPath.getInt("items.max{it.salary}.salary");

        System.out.println("max salary is : "+ maxSalary);

        System.out.println("===========================================");

        String  maxSalaryGuy = jsonPath.getString("items.max{it.salary}.first_name");

        System.out.println("maxSalaryGuy = " + maxSalaryGuy);

        System.out.println("===========================================");

        int minSalary = jsonPath.getInt("items.min{it.salary}.salary");

        System.out.println("min salary is : "+ minSalary);

        System.out.println("===========================================");

        String  minSalaryGuy = jsonPath.getString("items.min{it.salary}.first_name");

        System.out.println("minSalaryGuy = " + minSalaryGuy);
    }





}
