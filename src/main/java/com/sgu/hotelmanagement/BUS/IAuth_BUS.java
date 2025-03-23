package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DTO.Account;

public interface IAuth_BUS extends IBaseBUS<Account> {
    public boolean login(String username, String password);
    public boolean register(Account account);
    public boolean changePassword(String username, String oldPassword, String newPassword);
    public Account getAccountByUsername(String username);
}
