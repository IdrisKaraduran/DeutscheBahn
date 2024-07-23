package com.deutscheBahn.repository;

import com.deutscheBahn.entity.concreates.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone);

    Admin findByUsernameEquals(String username);
}
