package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DTO.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountBUSTest {
    IAuth_BUS accountBUS = new AccountBUS();
    @Test
    @Order(1)
    void register() {
        Account account = new Account(1,1,"admin", "admin@123", 1);
        assertTrue(accountBUS.register(account));
    }
    @Test
    @Order(2)
    void login() {
        assertTrue(accountBUS.login("admin", "admin@123"));
        //Notnull

    }

    @Test
    @Order(3)
    void getAccountByUsername() {
        Account account = accountBUS.getAccountByUsername("admin");
        System.out.println(account);
        assertNotNull(account);
    }

    @Test
    @Order(4)
    void getById() {
        Account account = accountBUS.getById(1);
        assertNotNull(account);
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(accountBUS.getAll());
    }

    @Test
    @Order(6)
    void changePassword() {
        assertTrue(accountBUS.changePassword("admin", "admin@123", "newpassword"));
    }
    @Test
    @Order(7)
    void delete() {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin@123");
        account.setUserId(1);
        assertTrue(accountBUS.delete(account));
    }


    @Test
    @Order(8)
    void create() {
        Account account = new Account(1,1,"admin", "admin@123", 1);
        assertFalse(accountBUS.create(account));
    }

    @Test
    @Order(9)
    void update() {
        Account account = new Account(1,1,"admin", "admin@123", 1);
        assertFalse(accountBUS.update(account));
    }


}