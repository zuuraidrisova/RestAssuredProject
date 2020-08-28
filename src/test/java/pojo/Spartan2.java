package pojo;

public class Spartan2 {


    /*
    so for easy config , we will just create another Spartan2 POJO
		with id , name , gender , phone fields to start with
		getters and setters
		no arg constructors , 4 arg constructors
		add toString method so we can print it out

     */
    private  int id;
    private  String name;
    private  String gender;
    private  long phone;

    public Spartan2(){

        //does not do anything bu required  for jackson to work
       //default constructor with no arguments
    }

    public Spartan2(int id, String name, String gender, long phone){

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){

        return "Spartan2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';

    }



}
