package com.deutscheBahn.security.service;

import com.deutscheBahn.entity.concreates.Admin;
import com.deutscheBahn.entity.concreates.User;
import com.deutscheBahn.repository.AdminRepository;
import com.deutscheBahn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user =  userRepository.findByUsernameEquals(username);
      Admin admin = adminRepository.findByUsernameEquals(username);

      if(user!=null){
          return new UserDetailsImpl(user.getId(),
                  user.getUsername(),
                  user.getFirstName(),
                  user.getLastName(),
                  user.getPassword(),
                  user.getUserRole().getRoleType().name());
      }else if(admin != null){
          return new UserDetailsImpl(admin.getId(),
                      admin.getUsername(),
                  admin.getFirstName(),
                  admin.getLastName(),
                  admin.getPassword(),
                  admin.getUserRole().getRoleType().name()
          );
      }
      throw new UsernameNotFoundException("User und Admin not found");

    }
}
