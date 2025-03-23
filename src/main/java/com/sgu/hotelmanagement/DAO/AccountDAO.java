package com.sgu.hotelmanagement.DAO;

import com.sgu.hotelmanagement.DTO.Account;
import com.sgu.hotelmanagement.Util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@Slf4j
public class AccountDAO implements IAuth_DAO{

    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM account WHERE username = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return PasswordUtil.checkPassword(password, hashedPassword);
            }
        } catch (Exception e) {
            log.error("Failed to check login: ", e);
        }
        return false;
    }

    @Override
    public boolean register(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO account (username, password, userId,role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, account.getUsername());
                ps.setString(2, PasswordUtil.hashPassword(account.getPassword()));
                ps.setInt(3, account.getUserId());
                ps.setInt(4, account.getRole());
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to register: ", e);
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
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE account SET password = ? WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, PasswordUtil.hashPassword(newPassword));
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, PasswordUtil.hashPassword(oldPassword));
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update password: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
        return false;
    }

    @Override
    public boolean create(Account obj) {
        return false;
    }

    @Override
    public boolean update(Account obj) {
        return false;
    }

    @Override
    public boolean delete(Account obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM account WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.executeUpdate();
                //Kiểm tra xem database có cập nhật không
                if (preparedStatement.getUpdateCount() == 0) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete account: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
        return false;
    }

    @Override
    public Account getById(int id) {
        try {
            String sql = "SELECT * FROM account WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setUserId(resultSet.getInt("userId"));
                return account;
            }
        } catch (Exception e) {
            log.error("Failed to get account by id: ", e);
        }
        return null;
    }

    @Override
    public ArrayList<Account> getAll() {
        try {
            String sql = "SELECT * FROM account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setUserId(resultSet.getInt("userId"));
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            log.error("Failed to get all accounts: ", e);
            return null;
        }
    }

    @Override
    public Account getAccountByUsername(String username) {
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setUserId(resultSet.getInt("userId"));
                account.setRole(resultSet.getInt("role"));
                return account;
            }
        } catch (Exception e) {
            log.error("Failed to get account by username: ", e);
        }
        return null;
    }
}
