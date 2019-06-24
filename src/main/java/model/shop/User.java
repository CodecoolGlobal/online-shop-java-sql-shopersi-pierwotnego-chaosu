package model.shop;

public class User {
     private int id;
     private String name;
     private String passowrd;
     private int isAdmin;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
          this.name = name;
     }

     public void setPassowrd(String passowrd) {
          this.passowrd = passowrd;
     }

     public void setAdmin(int admin) {
          isAdmin = admin;
     }

     public int getId() {
          return id;
     }

     public String getName() {
          return name;
     }

     public String getPassowrd() {
          return passowrd;
     }

     public int getAdmin() {
          return isAdmin;
     }

}
