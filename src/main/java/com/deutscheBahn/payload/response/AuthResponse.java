package com.deutscheBahn.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthResponse {

    private String username;
    private String role;
    private String token;
    private String firstName;
    private String lastName;


}
