package com.deutscheBahn.payload.response;

import com.deutscheBahn.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdminResponse implements Serializable {

    private Long userId;
    private String username;
    private String name;
    private String lastname;
    private LocalDateTime createdDate;
    private String email;
    private String phoneNumber;
    private Gender gender;


}
