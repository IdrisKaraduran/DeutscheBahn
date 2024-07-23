package com.deutscheBahn;

import com.deutscheBahn.entity.enums.Gender;
import com.deutscheBahn.entity.enums.RoleType;
import com.deutscheBahn.payload.request.AdminRequest;
import com.deutscheBahn.service.AdminService;
import com.deutscheBahn.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeutscheBahnApplication  {


	public static void main(String[] args) {

		SpringApplication.run(DeutscheBahnApplication.class, args);
	}


}
