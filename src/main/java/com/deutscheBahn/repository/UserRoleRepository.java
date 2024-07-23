package com.deutscheBahn.repository;

import com.deutscheBahn.entity.concreates.UserRole;
import com.deutscheBahn.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Query("select r from UserRole r where r.roleType = ?1")
    Optional<UserRole> findByERoleEquals(RoleType roleType);

    boolean existsByERoleEquals(RoleType roleType);

    boolean existsByRoleTypeEquals(RoleType roleType);

  

    Optional<UserRole> findByRoleEquals(RoleType roleType);

    boolean existsByRoleEquals(RoleType roleType);

    Optional<UserRole> findByRoleTypeEquals(RoleType roleType);
}
