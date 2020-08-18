package day01_Junit5Practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestPractice {
//hamcrest library is an assertion library, the aim of which is to make test more human readable
    //using lots of human readable matchers like something(somethingElse)
    //most importantly restAssured use hamcrest matcher when we chain multiple restAssured methods
        //hamcrest library already come with RestAssured dependency
    @Test
    public void test1(){

        //assert 5+4 is 9

        int num1 = 5;
        int num2 = 4;

        //we need these two imports to make this work
                //import static org.hamcrest.MatcherAssert.assertThat;
                //import static org.hamcrest.Matchers.*;

        //hamcrest library use the assertThat method for all assertions
        //hamcrest use built in matchers to do assertions
        //ex: is(some value),
            // equalTo(some value),
            // is(equalTo(some value)),


            // not(value) - negates the value
            // is( not (some value));
            // not(equalTo(some value));


        assertThat(num1 + num2, is(9));

        assertThat(num1 + num2, equalTo(9));

        assertThat(num1+num2, is( equalTo(9)));

        assertThat(num1 + num2, not(11));

        assertThat(num1 + num2, is( not(11)));

        assertThat(num1 + num2, not( equalTo( 11)));

        //save ur first name and last name into 2 variables
        //test the concatenation result using hamcrest matcher

        String firstName = "Zuura";
        String lastName = "Idrisova";

        assertThat(firstName+" "+lastName, is("Zuura Idrisova"));

        assertThat(firstName+" "+lastName, equalTo("Zuura Idrisova"));

        assertThat(firstName+" "+lastName, is(equalTo("Zuura Idrisova")));

        assertThat(firstName+" "+lastName, not("ZuuraIdrisova"));

        assertThat(firstName+" "+lastName, is(not("ZuuraIdrisova")));

        assertThat(firstName+" "+lastName, not( equalTo("ZuuraIdrisova")));


        //how to check in caseInsensitive manner
        assertThat(firstName,equalToIgnoringCase("zuura"));


        //how to ignore all whitespaces
        String name = "Zuura ";

        assertThat(name, equalToCompressingWhiteSpace("Zuura"));


        //assert using startsWith, endsWith, containsString methods
        assertThat(firstName, containsString("u"));

        assertThat(firstName, startsWith("Z"));

        assertThat(firstName, endsWith("a"));

        System.out.println("Success");


    }

    @DisplayName("Support for collections")
    @Test
    public void test2(){

        List<Integer> numlist = Arrays.asList(11,45,64,44,39,55);

        assertThat(numlist, hasSize(6));

        assertThat(numlist.size(), is(6));

        assertThat(numlist, hasItem(11));

        assertThat(numlist.get(3), is(44));

        assertThat(numlist,containsInRelativeOrder(11,44,55));

        assertThat(numlist, containsInAnyOrder(11,45,64,44,39,55));

        assertThat(numlist, contains(11,45,64,44,39,55));//this contains method works like equals

        System.out.println("Success");


    }


}
