package com.deutscheBahn.entity.abstracts;

import com.deutscheBahn.entity.enums.Gender;
import com.deutscheBahn.entity.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass//Ohne table in DB zu erstellen, schreibt man , um diese Class zu benutzen
@SuperBuilder//SubClass lar Eigenschaften dieser Class benutzen
public  abstract class BaseUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true )
    private String email;

    @Column(nullable = false, unique = true )
    private String phoneNumber;

    @Column(nullable = false)
    private RoleType userRole;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDateTime createdDate;

}

