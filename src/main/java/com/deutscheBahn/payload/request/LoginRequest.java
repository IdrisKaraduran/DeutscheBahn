package com.deutscheBahn.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "Sie mussen eingeben")
    private String username;

    @NotNull(message = "Sie mussen eingeben")
    private String password;
}
