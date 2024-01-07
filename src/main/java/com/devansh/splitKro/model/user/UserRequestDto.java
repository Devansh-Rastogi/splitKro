package com.devansh.splitKro.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private Long id;
    private String emailId;
    private String mobileNumber;
    private String name;
    private String password;
}
