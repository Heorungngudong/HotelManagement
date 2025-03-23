package com.sgu.hotelmanagement.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int staffId;
    private String staffName;
    private String staffAddress;
    private String staffPhone;
    private String staffEmail;
    private int staffPositionId;
    private String staffGender;
}
