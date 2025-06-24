package com.captainyun7.postappwithsecurity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    String username;
    String password;
    String role = "USER";
}
