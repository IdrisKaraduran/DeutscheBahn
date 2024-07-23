package com.deutscheBahn.entity.concreates;


import com.deutscheBahn.entity.abstracts.BaseUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SuperBuilder
public class User extends BaseUser {

    @Column(nullable = false)
    private boolean aktiv;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;




}
