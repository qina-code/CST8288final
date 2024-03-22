/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.User;

/**
 *
 * @author User
 */
public abstract class UserDAO {
    
    public abstract User getUserByEmail(String email);
    
    public abstract int createUser(User user);
}
