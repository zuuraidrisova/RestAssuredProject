package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations {

    private int location_id;
    private String street_address;
    private String postal_code;
    private String city;
    private String state_province;

    public  Locations(){

    }

    public Locations(int location_id, String street_address, String postal_code, String city, String state_province){

        this.location_id = location_id;
        this.street_address = street_address;
        this.postal_code = postal_code;
        this.city = city;
        this.street_address = street_address;

    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String  postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    @Override
    public String toString() {

        return "Locations{" +
                "location_id=" + location_id +
                ", street_address='" + street_address + '\'' +
                ", postal_code=" + postal_code +
                ", city='" + city + '\'' +
                ", state_province='" + state_province + '\'' +
                '}';
    }

    /*
       {
            "location_id": 2000,
            "street_address": "40-5-12 Laogianggen",
            "postal_code": "190518",
            "city": "Beijing",
            "state_province": null,
            "country_id": "CN",

            }
       */

}
