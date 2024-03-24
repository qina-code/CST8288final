/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author User
 */
import java.util.List;
import model.Item;
        
public interface ItemDAO {
    boolean addItem(Item item);
    boolean updateItemQuantity(int itemId, int newQuantity);
    List<Item> getItems();
    List<Item> getSurplusItems();
    
}
