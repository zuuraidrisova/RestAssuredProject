package day12_SchemaValidations_XML;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherEveryItem {

    @Test
    public void testList(){


        List<Integer> nums = Arrays.asList(11,5,3,4,6,8,9);

        assertThat(nums, everyItem(greaterThan(2)));

        assertThat(nums, everyItem(lessThan(20)));


        //assertThat(nums, allOf(greaterThan(0), lessThan(20)));

        List<String> items = Arrays.asList("ello", "than", "come", "ease");

        assertThat(items, everyItem(hasLength(4)) );


        String str  = "abc";

        assertThat(str, hasLength(3));

        int num = 100;
        //num is more than 50 or num is less than 0
        //for or situation we need anyOf()

        assertThat(num, anyOf(greaterThan(50), lessThan(0)));

        int num1 = 45;
        //num1 is more than 0 and  num1 is less than 50
        //for and situation we need allOf()

        assertThat(num1, allOf(greaterThan(0), lessThan(50)));

    }


}
