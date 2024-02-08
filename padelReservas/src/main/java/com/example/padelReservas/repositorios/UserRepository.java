package com.example.padelReservas.repositorios;

import com.example.padelReservas.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public ArrayList<User> findAll();
    User findByEmail(String email);
    public User findById(long id);
    public ArrayList<User> findByName(String nombre);
    public ArrayList<User> findByApellidos(String apellidos);
    public User findByTelefono(int telefono);
    public User save(User usuario);
}
