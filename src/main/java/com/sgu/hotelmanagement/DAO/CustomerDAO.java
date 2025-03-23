package com.sgu.hotelmanagement.DAO;

import com.sgu.hotelmanagement.DTO.Customer;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@Slf4j
public class CustomerDAO implements IBaseDAO<Customer>{
    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean create(Customer obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO customer (customerId,customerName, customerAddress, customerPhone, customerCCCD) VALUES (?, ?,?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getCustomerId());
                ps.setString(2, obj.getCustomerName());
                ps.setString(3, obj.getCustomerAddress());
                ps.setString(4, obj.getCustomerPhone());
                ps.setString(5, obj.getCustomerCCCD());


                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to create customer: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
    }

    @Override
    public boolean update(Customer obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE customer SET customerName = ?, customerAddress = ?, customerPhone = ?, customerCCCD = ? WHERE customerId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, obj.getCustomerName());
                ps.setString(2, obj.getCustomerAddress());
                ps.setString(3, obj.getCustomerPhone());
                ps.setString(4, obj.getCustomerCCCD());
                ps.setInt(5, obj.getCustomerId());
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update customer: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
    }

    @Override
    public boolean delete(Customer obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM customer WHERE customerId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getCustomerId());
                ps.executeUpdate();
                if(ps.getUpdateCount() == 0){
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete customer: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
        }
        }
    }

    @Override
    public Customer getById(int id) {
        try {
            String sql = "SELECT * FROM customer WHERE customerId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeQuery();
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    return Customer.builder()
                            .customerId(rs.getInt("customerId"))
                            .customerName(rs.getString("customerName"))
                            .customerAddress(rs.getString("customerAddress"))
                            .customerPhone(rs.getString("customerPhone"))
                            .customerCCCD(rs.getString("customerCCCD"))
                            .build();
                }
            }
        } catch (Exception e) {
            log.error("Failed to get customer by id: ", e);
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        try {
            String sql = "SELECT * FROM customer";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeQuery();
                ResultSet rs = ps.getResultSet();
                ArrayList<Customer> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(Customer.builder()
                            .customerId(rs.getInt("customerId"))
                            .customerName(rs.getString("customerName"))
                            .customerAddress(rs.getString("customerAddress"))
                            .customerPhone(rs.getString("customerPhone"))
                            .customerCCCD(rs.getString("customerCCCD"))
                            .build());
                }
                return list;
            }

        } catch (Exception e) {
            log.error("Failed to get all customers: ", e);
        }
        return null;
    }

}
