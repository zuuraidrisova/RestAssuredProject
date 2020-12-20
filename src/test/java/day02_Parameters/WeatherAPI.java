package day02_Parameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherAPI {


    @BeforeAll
    public static void setUp(){

        //api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
        baseURI = "https://api.openweathermap.org";
        basePath = "/data/2.5/weather?";


    }


    @DisplayName("learning weather forecast")
    @Test
    public void testChicagoWeather(){


        given().
                log().all().
                queryParam("q","Chicago").
                queryParam("appid", "f5f29b26ea4ccaffcf82d95eceeec9b1").
         when().
                get().
         then().
                statusCode(is(200)).
                log().all();
    }


}
