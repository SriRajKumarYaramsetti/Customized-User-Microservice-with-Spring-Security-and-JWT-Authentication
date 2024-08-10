package com.SriRaj.demo.Dtos;



import com.SriRaj.demo.Models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
}
