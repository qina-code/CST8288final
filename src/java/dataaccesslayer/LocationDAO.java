/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Location;

/**
 *
 * @author User
 */
public interface LocationDAO {
    int createLocation(Location location);
    int createLocation(String city, String postcal, int userId);
    Location getLocationByUserId(int userId);
}
