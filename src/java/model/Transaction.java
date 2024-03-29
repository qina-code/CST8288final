/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author User
 */
public class Transaction {

    private int orderId;
    private int quantity;
    private int purchaserId;
    private int itemInventoryId;
    private Timestamp transactionTime;
    private String itemName;

    public Transaction() {
        // Default constructor
    }

    public Transaction(int orderId, int quantity, int purchaserId, int itemInventoryId, Timestamp transactionTime) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.purchaserId = purchaserId;
        this.itemInventoryId = itemInventoryId;
        this.transactionTime = transactionTime;
    }
// Constructor with item name
    public Transaction(int orderId, int quantity, int purchaserId, int itemInventoryId, String itemName, Timestamp transactionTime) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.purchaserId = purchaserId;
        this.itemInventoryId = itemInventoryId;
        this.itemName = itemName;  // Set the item name
        this.transactionTime = transactionTime;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(int purchaserId) {
        this.purchaserId = purchaserId;
    }

    public int getItemInventoryId() {
        return itemInventoryId;
    }

    public void setItemInventoryId(int itemInventoryId) {
        this.itemInventoryId = itemInventoryId;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return "Transaction{" + "quantity=" + quantity + ", purchaserId=" + purchaserId + ", itemInventoryId=" + itemInventoryId + ", transactionTime=" + transactionTime + '}';
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
