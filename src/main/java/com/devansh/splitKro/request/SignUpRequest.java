package com.devansh.splitKro.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    public String mobileNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
