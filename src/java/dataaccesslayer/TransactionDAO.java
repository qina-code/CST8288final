/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.util.List;
import model.Transaction;

/**
 *
 * @author User
 */
public interface TransactionDAO {
    public List<Transaction> getTransactionsByPurchaserEmail(String email);
    public List<Transaction> getTransactionsByOwnerEmail(String email);
    public void createTransaction(Transaction transaction);
}
