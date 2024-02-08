package com.example.padelReservas.servicios.impl;

import com.example.padelReservas.modelo.User;
import com.example.padelReservas.dto.UserDto;
import com.example.padelReservas.modelo.Role;
import com.example.padelReservas.modelo.User;
import com.example.padelReservas.repositorios.RoleRepository;
import com.example.padelReservas.repositorios.UserRepository;
import com.example.padelReservas.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository repositorioUsers;
    public ArrayList<User> findAll(){

        return repositorioUsers.findAll();
    }
    public User findById(long id){

        return  repositorioUsers.findById(id);
    }

    public User findByTelefono(int telefono){

        return  repositorioUsers.findByTelefono(telefono);
    }
    public User save(User p){

        return repositorioUsers.save(p);
    }
    public ArrayList<User> findByName(String nombre){

        return repositorioUsers.findByName(nombre);
    }
    public ArrayList<User> findByApellidos(String apellido){

        return repositorioUsers.findByApellidos(apellido);
    }

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
