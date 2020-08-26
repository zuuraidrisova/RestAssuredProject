package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Countries {
    /*
     "items": [
        {
            "country_id": "AR",
            "country_name": "Argentina",
            "region_id": 2,
            "links": [
                {
                    "rel": "self",
                    "href": "http://54.174.216.245:1000/ords/hr/countries/AR"
                }
            ]
     */

    private String country_id;
    private String country_name;

    public Countries(){

    }

    public Countries(String country_id, String country_name){

        this.country_id = country_id;
        this.country_name = country_name;
    }


    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "country_id='" + country_id + '\'' +
                ", country_name='" + country_name + '\'' +
                '}';
    }



}
