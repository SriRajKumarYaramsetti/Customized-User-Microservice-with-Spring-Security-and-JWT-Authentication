package com.SriRaj.demo.Controllers;


import com.SriRaj.demo.Dtos.LoginRequestDto;
import com.SriRaj.demo.Dtos.SignUpRequestDto;
import com.SriRaj.demo.Dtos.UserResponseDto;
import com.SriRaj.demo.Models.User;
import com.SriRaj.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody SignUpRequestDto request){
        UserResponseDto userResponse=userService.createUser(request.getName(), request.getEmail(), request.getPassword(), request.getRoles());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginRequestDto request){
        userService.login(request.getName(),request.getPassword());

        return "Success from controller";
    }
}
