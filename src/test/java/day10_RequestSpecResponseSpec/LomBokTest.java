package day10_RequestSpecResponseSpec;

import org.junit.jupiter.api.Test;

import pojo.Category;
import pojo.CountriesHR;

public class LomBokTest {

    @Test
    public void Test(){


        Category category = new Category("12", "abc");

        System.out.println("category = " + category);

        CountriesHR country = new CountriesHR("AR", "ARGENTINA",12);

        System.out.println("country = " + country);
    }
}
