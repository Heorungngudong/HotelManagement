package com.sgu.hotelmanagement.DAO;

import com.sgu.hotelmanagement.DTO.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDAOTest {
    IAuth_DAO accountDAO = new AccountDAO();
    @Test
    @Order(1)
    void register() {
        Account account = new Account(1,1,"admin", "admin@123", 1);
        assertTrue(accountDAO.register(account));
    }
    @Test
    @Order(2)
    void login() {
        assertTrue(accountDAO.login("admin", "admin@123"));
        //Notnull
        Account account = accountDAO.getAccountByUsername("admin");
        System.out.println(account);
        assertNotNull(account);
    }

    @Test
    @Order(3)
    void changePassword() {
        assertTrue(accountDAO.changePassword("admin", "admin@123", "newpassword"));
    }
    @Test
    @Order(4)
    void delete() {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin@123");
        account.setUserId(1);
        assertTrue(accountDAO.delete(account));
    }
}