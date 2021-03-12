package com.example.demo.Service;


import com.example.demo.Model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {
    boolean create(Client client);
    List<Client> readAll();
    Client read(long id);
    boolean update(Client client, long id);
    boolean delete(long id);
}
