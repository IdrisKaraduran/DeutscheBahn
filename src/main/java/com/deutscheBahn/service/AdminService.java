package com.deutscheBahn.service;

import com.deutscheBahn.entity.concreates.Admin;
import com.deutscheBahn.entity.enums.RoleType;
import com.deutscheBahn.exception.ConflictException;
import com.deutscheBahn.payload.request.AdminRequest;
import com.deutscheBahn.payload.response.AdminResponse;
import com.deutscheBahn.payload.response.ResponseMessage;
import com.deutscheBahn.repository.AdminRepository;
import com.deutscheBahn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public ResponseMessage save(AdminRequest request) {
        //var mi diye bakiyoruz
        checkDuplicate(request.getUsername(),request.getPhoneNumber(),request.getEmail());
        //admin nesnesi olusturuluyor.
        Admin admin = createAdminForSave(request);
        admin.setBuilt_in(false);

        if(Objects.equals(request.getUsername(),"ADMIN")) admin.setBuilt_in(true);

        //Role Setleniyor
         admin.setUserRole(RoleType.ADMIN);

         //Tarih Setleniyor.
        admin.setCreatedDate(LocalDateTime.now());

         //Not:password encode ediliyor.
       // admin.setPassword(passwordEncoder.encode(admin.getPassword()));


       Admin saveData = adminRepository.save(admin);

       return ResponseMessage.<AdminResponse>builder()
               .message("Admin saved")
               .httpStatus(HttpStatus.CREATED)
               .object(createResponse(saveData))//Pojo dto cevrilmesi lazim
               .build();

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

    public Admin createAdminForSave(AdminRequest request){
        return Admin.builder().
                username(request.getUsername()).
                firstName(request.getFirstName()).
                password(request.getPassword()).
                lastName(request.getLastName()).
                email(request.getEmail()).
                phoneNumber(request.getPhoneNumber()).
                gender(request.getGender()).build();


    }
    private AdminResponse createResponse(Admin admin){
        return AdminResponse.builder()
                .userId(admin.getId())
                .username(admin.getUsername())
                .name(admin.getFirstName())
                .lastname(admin.getLastName())
                .email(admin.getEmail())
                .createdDate(admin.getCreatedDate())
                .phoneNumber(admin.getPhoneNumber())
                .gender(admin.getGender())
                .build();

    }

    // !!! Runner tarafi icin yazildi
    public long countAllAdmin() {

        return adminRepository.count();
    }





}
