package com.sgu.hotelmanagement.DAO;

import com.sgu.hotelmanagement.DTO.Account;

public interface IAuth_DAO extends IBaseDAO<Account> {
    public boolean login(String username, String password);
    public boolean register(Account account);
    public boolean changePassword(String username, String oldPassword, String newPassword);
    public Account getAccountByUsername(String username);
}
