package com.example.demo.Repository;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByName(String username);
    Client findClientById(long id);
}
