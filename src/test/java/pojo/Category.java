package pojo;

import lombok.*;

@Getter //this annotation will add getters to all fields
@Setter //this annotation will add setters to all fields
@AllArgsConstructor //this annotation will add constructors with all arguments
@NoArgsConstructor //this annotation will add constructors with no arguments
@ToString //this annotation will add toString method  with all arguments
public class Category {

    private String id;
    private String name;


//    public Category(){
//
//    }
//
//    public Category(String id, String name){
//
//        this.id = id;
//        this.name = name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Category{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }


}
