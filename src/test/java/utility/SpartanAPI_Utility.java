package utility;

import com.github.javafaker.Faker;
import pojo.Spartan;

public class SpartanAPI_Utility {

    protected static Spartan createRandomSpartanObject() {

        Faker faker = new Faker();

        String name   = faker.name().firstName();

        String gender = faker.demographic().sex();

        // always getting long number outside range of int to avoid errors

        long phone    = faker.number().numberBetween(5000000000l,9999999999L);
        Spartan randomSpartanObject = new Spartan(name,gender,phone);

        System.out.println("Created Random Spartan Object : " + randomSpartanObject);
        return randomSpartanObject ;
    }
}
