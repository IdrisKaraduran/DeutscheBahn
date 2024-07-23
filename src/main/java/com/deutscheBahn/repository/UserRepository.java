package com.deutscheBahn.repository;

import com.deutscheBahn.entity.concreates.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone);

    User findByUsernameEquals(String username);
}
