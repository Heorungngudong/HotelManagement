package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DAO.StaffDAO;
import com.sgu.hotelmanagement.DTO.Staff;

import java.util.ArrayList;

public class StaffBUS implements IBaseBUS<Staff>{
    StaffDAO staffDAO = new StaffDAO();

    @Override
    public boolean create(Staff object) {
        return staffDAO.create(object);
    }

    @Override
    public Staff getById(int id) {
        return staffDAO.getById(id);
    }

    @Override
    public boolean update(Staff object) {
        return staffDAO.update(object);
    }

    @Override
    public boolean delete(Staff object) {
        return staffDAO.delete(object);
    }

    @Override
    public ArrayList<Staff> getAll() {
        return staffDAO.getAll();
    }


}
