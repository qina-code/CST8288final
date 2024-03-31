/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Location {
    private int id;
    private String city;
    private String postal;
    private int userId;

    public Location(int id, String city, String postal, int userId) {
        this.id = id;
        this.city = city;
        this.postal = postal;
        this.userId = userId;
    }
    
        public Location(String city, String postal, int userId) {
        this.city = city;
        this.postal = postal;
        this.userId = userId;
    }
    
    public Location( String city, String postal) {
        this.city = city;
        this.postal = postal;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
    
        public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
}
