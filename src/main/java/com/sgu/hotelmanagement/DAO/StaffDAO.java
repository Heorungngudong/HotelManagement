package com.sgu.hotelmanagement.DAO;

import com.sgu.hotelmanagement.DTO.Staff;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@Slf4j
public class StaffDAO implements IBaseDAO<Staff> {
    Connection connection = Connect.getInstance().getConnection();

    public boolean create(Staff obj) {
     try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO staff (staffId,staffName, staffAddress, staffPhone, staffEmail, staffPositionId, staffGender) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getStaffId());
                ps.setString(2, obj.getStaffName());
                ps.setString(3, obj.getStaffAddress());
                ps.setString(4, obj.getStaffPhone());
                ps.setString(5, obj.getStaffEmail());
                ps.setInt(6, obj.getStaffPositionId());
                ps.setString(7, obj.getStaffGender());




                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to create staff: ", e);
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

    public boolean update(Staff obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE staff SET staffName = ?, staffAddress = ?, staffPhone = ?, staffEmail = ?, staffPositionId = ?, staffGender = ? WHERE staffId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, obj.getStaffName());
                ps.setString(2, obj.getStaffAddress());
                ps.setString(3, obj.getStaffPhone());
                ps.setString(4, obj.getStaffEmail());
                ps.setInt(5, obj.getStaffPositionId());
                ps.setString(6, obj.getStaffGender());
                ps.setInt(7, obj.getStaffId());
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update staff: ", e);
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

    public boolean delete(Staff obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM staff WHERE staffId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getStaffId());
                ps.executeUpdate();
                if(ps.getUpdateCount() == 0){
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete staff: ", e);
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

    public Staff getById(int id) {
        try {
            String sql = "SELECT * FROM staff WHERE staffId = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    return Staff.builder()
                            .staffId(rs.getInt("staffId"))
                            .staffName(rs.getString("staffName"))
                            .staffAddress(rs.getString("staffAddress"))
                            .staffPhone(rs.getString("staffPhone"))
                            .staffEmail(rs.getString("staffEmail"))
                            .staffPositionId(rs.getInt("staffPositionId"))
                            .staffGender(rs.getString("staffGender"))
                            .build();
                }
            }
        } catch (Exception e) {
            log.error("Failed to get staff by id: ", e);
        }
        return null;
    }

    public ArrayList<Staff> getAll() {
        try {
            String sql = "SELECT * FROM staff";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                var rs = ps.executeQuery();
                ArrayList<Staff> staffs = new ArrayList<>();
                while (rs.next()) {
                    staffs.add(Staff.builder()
                            .staffId(rs.getInt("staffId"))
                            .staffName(rs.getString("staffName"))
                            .staffAddress(rs.getString("staffAddress"))
                            .staffPhone(rs.getString("staffPhone"))
                            .staffEmail(rs.getString("staffEmail"))
                            .staffPositionId(rs.getInt("staffPositionId"))
                            .staffGender(rs.getString("staffGender"))
                            .build());
                }
                return staffs;
            }
        } catch (Exception e) {
            log.error("Failed to get all staffs: ", e);
            return null;
        }
    }


}
