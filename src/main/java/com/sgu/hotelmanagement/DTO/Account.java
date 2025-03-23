package com.sgu.hotelmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int accountId;
    private int userId;
    private String username;
    private String password;
    private int role;
}
