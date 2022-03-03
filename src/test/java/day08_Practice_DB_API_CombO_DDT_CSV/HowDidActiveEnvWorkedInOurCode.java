package day08_Practice_DB_API_CombO_DDT_CSV;

import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

public class HowDidActiveEnvWorkedInOurCode {

    @Test
    public void test(){

        //print out all library1 info if active_env = library1

        String current_env = "spartan1";
        //ConfigurationReader.getProperty("active_env");

        System.out.println("current_env = " + current_env);

        String db1URL = ConfigurationReader.getProperty(current_env+".database.url");

        System.out.println("db1URL = " + db1URL);


    }

}
