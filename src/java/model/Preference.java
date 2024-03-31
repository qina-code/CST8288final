/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Preference {
    private int id;
    private int categoryId;
    private int userId;

    public Preference(int id, int categoryId, int userId) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
    }
    
    public Preference(int categoryId, int userId) {
        this.categoryId = categoryId;
        this.userId = userId;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    
}
