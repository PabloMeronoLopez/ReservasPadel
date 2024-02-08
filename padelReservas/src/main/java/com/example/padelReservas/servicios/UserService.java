package com.example.padelReservas.servicios;

import com.example.padelReservas.modelo.User;
import com.example.padelReservas.dto.UserDto;
import com.example.padelReservas.modelo.User;

import java.util.List;

public interface  UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
