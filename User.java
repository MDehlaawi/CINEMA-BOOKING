
package com.mycompany.apui;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity

public class User implements java.io.Serializable {
   @Id
   @Column(name="F_name")
   private String f_name;
   @Column(name="L_name")
   private String l_name;
    @Column(name="PhoneNo")
   private String phoneNo;
   @Column(name="Email")
   private String email;
   @Column(name="Password")
   private String password;
   
   public User() {}
//empty constructor

    public User(String f_name, String l_name, String phoneNo, String email, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }
    public User(String f_name, String l_name){
        this.f_name = f_name;
        this.l_name = l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
   

   
   
   
}//end class
