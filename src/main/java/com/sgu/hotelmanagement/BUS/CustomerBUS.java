package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DAO.CustomerDAO;
import com.sgu.hotelmanagement.DTO.Customer;

import java.util.ArrayList;

public class CustomerBUS implements IBaseBUS<Customer> {
    CustomerDAO customerDAO = new CustomerDAO();
    @Override
    public boolean create(Customer object) {
        return customerDAO.create(object);
    }

    @Override
    public Customer getById(int id) {
        return customerDAO.getById(id);
    }

    @Override
    public boolean update(Customer object) {
        return customerDAO.update(object);
    }

    @Override
    public boolean delete(Customer object) {
        return customerDAO.delete(object);
    }

    @Override
    public ArrayList<Customer> getAll() {
        return customerDAO.getAll();
    }
}
