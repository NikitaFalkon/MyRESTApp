package com.example.demo.Service;



import com.example.demo.Model.Client;


import java.util.List;

public interface ClientService {
    boolean create(Client client);
    List<Client> readAll();
    Client read(int id);
    boolean update(Client client, int id);
    boolean delete(int id);
}
