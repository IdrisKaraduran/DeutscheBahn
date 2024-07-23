package com.deutscheBahn.entity.concreates;

import com.deutscheBahn.entity.abstracts.BaseUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "admins")

public class Admin extends BaseUser {


    private boolean built_in;

}