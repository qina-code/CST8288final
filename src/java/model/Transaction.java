/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author User
 */
public class Transaction {
    private int quantity;
    private int purchaserId;
    private int itemInventoryId;
    private Date transactionTime;

    public Transaction(int quantity, int purchaserId, int itemInventoryId, Date transactionTime) {
        this.quantity = quantity;
        this.purchaserId = purchaserId;
        this.itemInventoryId = itemInventoryId;
        this.transactionTime = transactionTime;
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

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return "Transaction{" + "quantity=" + quantity + ", purchaserId=" + purchaserId + ", itemInventoryId=" + itemInventoryId + ", transactionTime=" + transactionTime + '}';
    }
    
    

}
