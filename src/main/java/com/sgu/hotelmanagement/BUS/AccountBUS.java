package com.sgu.hotelmanagement.BUS;


import com.sgu.hotelmanagement.DAO.AccountDAO;
import com.sgu.hotelmanagement.DTO.Account;

import java.util.ArrayList;

public class AccountBUS implements IAuth_BUS {
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public boolean login(String username, String password) {
        return accountDAO.login(username, password);
    }
    @Override
    public boolean register(Account account) {
        return accountDAO.register(account);
    }
    @Override
    public boolean changePassword(String username, String oldPassword, String password) {
        return accountDAO.changePassword(username, oldPassword, password);
    }

    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    @Override
    public boolean create(Account object) {
        return accountDAO.create(object);
    }

    @Override
    public boolean update(Account object) {
        return accountDAO.update(object);
    }

    @Override
    public boolean delete(Account object) {
        return accountDAO.delete(object);
    }
    @Override
    public Account getById(int id) {
        return accountDAO.getById(id);
    }
    @Override
    public ArrayList<Account> getAll() {
        return accountDAO.getAll();
    }

}
