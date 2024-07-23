package com.deutscheBahn.service;

import com.deutscheBahn.entity.concreates.User;
import com.deutscheBahn.entity.enums.RoleType;
import com.deutscheBahn.exception.ConflictException;
import com.deutscheBahn.payload.request.UserRequest;
import com.deutscheBahn.payload.response.UserResponse;
import com.deutscheBahn.repository.AdminRepository;
import com.deutscheBahn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    public ResponseEntity<UserResponse> save(UserRequest userRequest) {
        //Zuerst müssen wir kontrollieren, ob diese User früher gespeichert wurde.
        checkDuplicate(userRequest.getUsername(),userRequest.getPhoneNumber(),userRequest.getEmail());

        User user= createUserForSave(userRequest);

        //Role Setleniyor
        user.setUserRole(RoleType.USER);

        //Not:password encode ediliyor.
       // user.setPassword(passwordEncoder.encode(user.getPassword()));

        //Tarih setleniyor
        user.setCreatedDate(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        UserResponse userResponse = createUserResponseForReturn(savedUser);

        return ResponseEntity.ok(userResponse);
    }

    public void checkDuplicate(String username,String phone,String email){
        if(adminRepository.existsByUsername(username) ||
                adminRepository.existsByPhoneNumber(phone) ||
                adminRepository.existsByEmail(email)){
            throw new ConflictException("Zaten Var");
        }else if(userRepository.existsByUsername(username) ||
                userRepository.existsByPhoneNumber(phone) ||
                userRepository.existsByEmail(email)){
            throw new ConflictException("Zaten Var");
        }
    }

    public User createUserForSave(UserRequest request){
        return User.builder().
                username(request.getUsername()).
                firstName(request.getFirstName()).
                password(request.getPassword()).
                lastName(request.getLastName()).
                email(request.getEmail()).
                phoneNumber(request.getPhoneNumber()).
                gender(request.getGender()).build();


    }
    public UserResponse createUserResponseForReturn(User user){

        return UserResponse.builder().
                userId(user.getId()).
                username(user.getUsername()).
                name(user.getFirstName()).
                lastname(user.getLastName()).
                createdDate(user.getCreatedDate()).
                email(user.getEmail()).
                phoneNumber(user.getPhoneNumber()).
                gender(user.getGender()).build();
    }






}
