/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Transaction;

/**
 *
 * @author User
 */
public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public List<Transaction> getTransactionsByPurchaserEmail(String email) {
        Connection connection = DBConnection.getConnection();
        int userId = -1;
        List<Transaction> purchaserTransactions = new ArrayList<>();
        try {
            String userQuery = "SELECT * FROM user WHERE email = ?";
            PreparedStatement preparedUserStatement = connection.prepareStatement(userQuery);
            preparedUserStatement.setString(1, email);

            ResultSet userResultSet = preparedUserStatement.executeQuery();

            if (userResultSet.next()) {
                userId = userResultSet.getInt("id");
            }

            String transactionQuery = "SELECT t.order_id, t.item_id, t.quantity, t.purchaser_id, t.transaction_time, i.name AS item_name "
                    + "FROM transaction t "
                    + "JOIN itemInventory i ON t.item_id = i.id "
                    + "WHERE t.purchaser_id = ? "
                    + "ORDER BY t.transaction_time ";
            PreparedStatement preparedPurchaserStatement = connection.prepareStatement(transactionQuery);
            preparedPurchaserStatement.setInt(1, userId);

            ResultSet purchaserResultSet = preparedPurchaserStatement.executeQuery();

            while (purchaserResultSet.next()) {
                int orderId = purchaserResultSet.getInt("order_id");
                int itemId = purchaserResultSet.getInt("item_id");
                int quantity = purchaserResultSet.getInt("quantity");
                int purchaserId = purchaserResultSet.getInt("purchaser_id");
                Timestamp transactionTime = purchaserResultSet.getTimestamp("transaction_time");
                String itemName = purchaserResultSet.getString("item_name");
                Transaction t = new Transaction(orderId, quantity, purchaserId, itemId, itemName, transactionTime);
                purchaserTransactions.add(t);

            }
            System.out.println(purchaserTransactions);
            return purchaserTransactions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionsByOwnerEmail(String email) {
        Connection connection = DBConnection.getConnection();
        int userId = -1;
        List<Transaction> retailerTransactions = new ArrayList<>();
        try {
            String userQuery = "SELECT * FROM user WHERE email = ?";
            PreparedStatement preparedUserStatement = connection.prepareStatement(userQuery);
            preparedUserStatement.setString(1, email);

            ResultSet userResultSet = preparedUserStatement.executeQuery();

            if (userResultSet.next()) {
                userId = userResultSet.getInt("id");
            }

            String transactionQuery = "SELECT  transaction.item_id AS item_id, transaction.quantity AS quantity, "
                    + "transaction.purchaser_id AS purchaser_id, transaction.transaction_time AS transaction_time"
                    + " FROM transaction JOIN itemInventory ON transaction.item_id = itemInventory.id  WHERE owner_id = ? ";
            PreparedStatement preparedRetailerStatement = connection.prepareStatement(transactionQuery);
            preparedRetailerStatement.setInt(1, userId);

            ResultSet retailerTransactionResultSet = preparedRetailerStatement.executeQuery();

            while (retailerTransactionResultSet.next()) {
                int orderId = retailerTransactionResultSet.getInt("order_id");
                int quantity = retailerTransactionResultSet.getInt("quantity");
                int itemId = retailerTransactionResultSet.getInt("item_id");
                int purchaserId = retailerTransactionResultSet.getInt("purchaser_id");
                Timestamp transactionTime = retailerTransactionResultSet.getTimestamp("transaction_time");

                Transaction t = new Transaction(orderId, quantity, itemId, purchaserId, transactionTime);

                retailerTransactions.add(t);
            }

            return retailerTransactions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createTransaction(Transaction transaction) {
        Connection connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO transaction ( item_id, quantity, purchaser_id, transaction_time, order_id) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, transaction.getItemInventoryId());
            preparedStatement.setInt(2, transaction.getQuantity());
            preparedStatement.setInt(3, transaction.getPurchaserId());
            preparedStatement.setTimestamp(4, transaction.getTransactionTime());
            preparedStatement.setInt(5, transaction.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
