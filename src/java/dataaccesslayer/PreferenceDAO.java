/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Location;
import model.Preference;

/**
 *
 * @author User
 */
public interface PreferenceDAO {
    int createPreference(Preference preference);
    int createPreference(int categoryId, int userId);
    Preference getPreferenceByUserId(int userId);
}
