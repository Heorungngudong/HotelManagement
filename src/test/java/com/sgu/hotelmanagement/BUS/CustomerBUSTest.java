package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DTO.Customer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerBUSTest {
    IBaseBUS<Customer> customerBUS = new CustomerBUS();
    Customer customer = Customer.builder()
            .customerId(1)
            .customerName("Nguyen Van A")
            .customerPhone("0123456789")
            .customerAddress("123 Nguyen Van Linh")
            .customerCCCD("33333333")
            .build();
    @Test
    @Order(1)
    void create() {

        assertTrue(customerBUS.create(customer));
    }

    @Test
    @Order(2)
    void getById() {
        assertNotNull(customerBUS.getById(1));
    }

    @Test
    @Order(3)
    void update() {
        customer.setCustomerName("Nguyen Van B");
        assertTrue(customerBUS.update(customer));
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(customerBUS.getAll());
    }
    @Test
    @Order(5)
    void delete() {
        assertTrue(customerBUS.delete(customer));
    }


}