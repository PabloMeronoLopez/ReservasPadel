package com.example.padelReservas.controladores;

import com.example.padelReservas.servicios.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Principal {
    private UserService userService;

    public Principal(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/principal")
    public String principal(){
        return "principal";
    }

}
