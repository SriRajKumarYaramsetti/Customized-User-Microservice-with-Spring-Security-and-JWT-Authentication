package com.SriRaj.demo.Services;


import com.SriRaj.demo.Dtos.UserResponseDto;
import com.SriRaj.demo.Models.Role;
import com.SriRaj.demo.Models.User;
import com.SriRaj.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secretKey;


    @Autowired
    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationManager authenticationManager){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationManager=authenticationManager;
    }

    public UserResponseDto convertToUserResponseDto(User user){
        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());
        return responseDto;

    }
    public UserResponseDto createUser(String name, String email, String password, List<Role> roles) {

        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);
        User savedUser=userRepository.save(user);

        return convertToUserResponseDto(savedUser);
    }

    public String login(String username,String password){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        if (authentication.isAuthenticated()){
            return "Success";
        }
        return "fail";
    }
}
