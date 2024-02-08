package com.example.padelReservas.controladores;

import com.example.padelReservas.modelo.Pista;
import com.example.padelReservas.modelo.Reserva;
import com.example.padelReservas.modelo.Turno;
import com.example.padelReservas.modelo.User;
import com.example.padelReservas.servicios.ServicioPistas;
import com.example.padelReservas.servicios.ServicioReservas;
import com.example.padelReservas.servicios.ServicioTurno;
import com.example.padelReservas.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class CrudReservas {
    private UserService userService;
    @Autowired
    ServicioPistas servicioPistas;
    @Autowired
    ServicioReservas servicioReservas;
    @Autowired
    ServicioTurno servicioTurno;


    public CrudReservas(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users" )
    public String listRegisteredUsers(Model model,
                                      @RequestParam(value = "selectedFecha", required = false) LocalDate selectedFecha) {
        ArrayList<Turno> turnos=servicioTurno.findAll();
        model.addAttribute("listaTurno", turnos);
        model.addAttribute("selectedFecha", selectedFecha);

        return "/users";
    }
    @PostMapping("/users")
    public String processForm(@RequestParam("turno") long selectedTurno, @RequestParam("selectedFecha") LocalDate selectedFecha, Model model, Authentication authentication) {
        model.addAttribute("selectedTurno", selectedTurno);
        model.addAttribute("selectedFecha", selectedFecha);


        Turno turno = new Turno();
        turno.setIdTurno(selectedTurno);

        ArrayList<Pista> pistas = servicioPistas.findAll();
        ArrayList<Pista> pistasFiltradas = new ArrayList<>();

        if (selectedFecha != null) {
            ArrayList<Reserva> pistasOcupadas = servicioReservas.findByTurnoAndFecha(turno, selectedFecha);

            for (Pista pista : pistas) {
                boolean ocupada = pistasOcupadas.stream()
                        .anyMatch(reserva -> reserva.getPista().getIdPista() == pista.getIdPista());

                if (!ocupada) {
                    pistasFiltradas.add(pista);
                }
            }
        } else {

            pistasFiltradas.addAll(pistas);
        }
        Reserva reserva=new Reserva();
        User usuario=userService.findByEmail(authentication.getName());
        reserva.setFecha(selectedFecha);
        reserva.setPista(new Pista());
        reserva.setTurno(servicioTurno.findById(selectedTurno));
        reserva.setUsuario(usuario);
        model.addAttribute("reserva", reserva);
        model.addAttribute("lista", pistasFiltradas);

        return "reservas";
        //return "redirect:/users?selectedTurno=" + turno + "&selectedFecha=" + fecha;
    }
    @PostMapping("/users/reservas")
    public String reservarPista(@RequestParam("selectedFecha") LocalDate selectedFecha,
                                @RequestParam("idPista") long idPista,
                                @RequestParam("selectedTurno") long selectedTurno,
                                Authentication authentication) {

        Reserva rese=new Reserva();

        User usuario=userService.findByEmail(authentication.getName());
        rese.setUsuario(usuario);
        rese.setTurno(servicioTurno.findById(selectedTurno));
        rese.setFecha(selectedFecha);
        rese.setPista(servicioPistas.findById(idPista));
        servicioReservas.save(rese);
        return "redirect:/users";
    }
}
