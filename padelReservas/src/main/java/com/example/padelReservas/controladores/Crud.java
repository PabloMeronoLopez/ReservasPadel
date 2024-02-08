package com.example.padelReservas.controladores;

import com.example.padelReservas.modelo.Reserva;
import com.example.padelReservas.modelo.User;
import com.example.padelReservas.servicios.ServicioReservas;
import com.example.padelReservas.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class Crud {
    private UserService userService;
    @Autowired
    ServicioReservas servicioReservas;


    public Crud(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/crud")
    public String listadoReservas(Authentication authentication, Model model){
        User usuario=userService.findByEmail(authentication.getName());
        ArrayList<Reserva> reservas=servicioReservas.findByUsuario(usuario);
        ArrayList<Reserva> reservasPendientes=new ArrayList<>();
        for (int i=0;i<reservas.size();i++){
            if(reservas.get(i).getFecha().isAfter(LocalDate.now())){
                reservasPendientes.add(reservas.get(i));
            }
        }
        model.addAttribute("reservasPendientes", reservasPendientes);
        model.addAttribute("reservas", reservas);

        return "crud";
    }
    @GetMapping("/crud/delete/{id}")
    public String borrarReserva(@PathVariable long id, Model model){
        Reserva p=servicioReservas.findById(id);
        servicioReservas.delete(p);
        return "redirect:/crud";
    }
}
