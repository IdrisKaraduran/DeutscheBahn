package com.deutscheBahn.service;

import com.deutscheBahn.entity.concreates.UserRole;
import com.deutscheBahn.entity.enums.RoleType;
import com.deutscheBahn.exception.ConflictException;
import com.deutscheBahn.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    public UserRole getUserRole(RoleType roleType) {
       Optional<UserRole> userRole = userRoleRepository.findByRoleTypeEquals(roleType);
       return userRole.orElse(null);
    }

    // Runner tarafi icin gerekli method
    public List<UserRole> getAllUserRole() {
        return userRoleRepository.findAll();
    }

    // Runner tarafi icin gerekli method
    public UserRole save(RoleType roleType) {

        if(userRoleRepository.existsByRoleTypeEquals(roleType)) {
            throw new ConflictException("This role is already registered");
        }

        UserRole userRole = UserRole.builder().roleType(roleType).build();
        return userRoleRepository.save(userRole);
    }



}
