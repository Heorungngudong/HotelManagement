package com.sgu.hotelmanagement.BUS;

import com.sgu.hotelmanagement.DTO.Staff;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StaffBUSTest {
    IBaseBUS<Staff> staffBUS = new StaffBUS();
    Staff staff = Staff.builder()
            .staffId(1)
            .staffName("Nguyen Van A")
            .staffAddress("123 Nguyen Van Linh")
            .staffPhone("0123456789")
            .staffEmail("magil@gmail.com")
            .staffPositionId(1)
            .staffGender("Male")
            .build();
    @Test
    @Order(1)
    void create() {
        assertTrue(staffBUS.create(staff));
    }

    @Test
    @Order(2)
    void getById() {
        assertNotNull(staffBUS.getById(1));
        assertEquals(staff, staffBUS.getById(1));
    }

    @Test
    @Order(3)
    void update() {
        staff.setStaffName("Nguyen Van B");
        assertTrue(staffBUS.update(staff));
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull( staffBUS.getAll());
    }

    @Test
    @Order(5)
    void delete() {
        assertTrue(staffBUS.delete(staff));
    }

}