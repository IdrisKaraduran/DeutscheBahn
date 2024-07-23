package com.deutscheBahn.controller;


import com.deutscheBahn.payload.request.AdminRequest;
import com.deutscheBahn.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    //save

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody AdminRequest request){

        return ResponseEntity.ok(adminService.save(request));
    }






}
