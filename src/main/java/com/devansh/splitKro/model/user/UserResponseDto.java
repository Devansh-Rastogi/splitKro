package com.devansh.splitKro.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String emailId;
    private String mobileNumber;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.emailId = user.getEmailId();
        this.mobileNumber = user.getMobileNumber();
    }
}