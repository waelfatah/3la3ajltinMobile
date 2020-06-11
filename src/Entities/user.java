/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author suare
 */
public class user {
     protected int id;
    protected String username;
    protected String lastname;
    protected String firstname;
    protected String image;
    protected String email;
    protected String password;
    protected String roles;
    protected String birthDay;
    protected int phone;
    protected String pays;
    protected String adress;
    protected String gender;
    public user(){
        
    }

    public user(String lastname, String firstname, String email, String roles, String birthDay, int phone, String pays, String adress, String gender) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.roles = roles;
        this.birthDay = birthDay;
        this.phone = phone;
        this.pays = pays;
        this.adress = adress;
        this.gender = gender;
    }

    public user(String lastname, String firstname, String email, String birthDay, int phone, String pays, String adress, String gender) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.birthDay = birthDay;
        this.phone = phone;
        this.pays = pays;
        this.adress = adress;
        this.gender = gender;
    }           


    public user(String username, String firstname, String lastname, String email, String roles,String birthDay,int phone,String pays, String adress, String gender) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.roles = roles;
        this.phone = phone;
        this.adress = adress;
        this.gender = gender;
        this.birthDay=birthDay;
        this.pays=pays;
    }
    
    

    public user(String username, String image, String email, String password) {
        this.username = username;
        this.image = image;
        this.email = email;
        this.password = password;
    }

    public user(int id, String username, String lastname, String firstname, String image, String email, String password, String roles, String birthDay, int phone, String pays, String adress, String gender) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.image = image;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.birthDay = birthDay;
        this.phone = phone;
        this.pays = pays;
        this.adress = adress;
        this.gender = gender;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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



    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + ", username=" + username + ", lastname=" + lastname + ", firstname=" + firstname + ", image=" + image + ", email=" + email + ", password=" + password + ", roles=" + roles + ", birthDay=" + birthDay + ", phone=" + phone + ", pays=" + pays + ", adress=" + adress + ", gender=" + gender + '}';
    }

}
