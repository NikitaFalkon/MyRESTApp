package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Model.Role;
import com.example.demo.Repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl  {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private long CLIENT_ID_HOLDER;

    public boolean create(Client client) {
        final long clientId = ++CLIENT_ID_HOLDER;
        client.setId(clientId);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
       // client.setRoles(Collections.singleton(Role.ADMIN));
        clientRepository.save(client);
        return true;
    }
    


    public List<Client> readAll() {
        return clientRepository.findAll();
    }


    public Client read(long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public boolean update(Client client, long id) {
        //Client client1 = clientRepository.findById(id).orElseThrow();
        Client client1 = clientRepository.findClientById(id);
        if (client1!=null)
        {
            BeanUtils.copyProperties(client, client1, "id");
            client1.setPassword(passwordEncoder.encode(client1.getPassword()));
            clientRepository.save(client1);

            return true;
        }
            return false;
    }
    public Client login(Client client)
    {
        Client client1 = (Client) loadUserByUsername(client.getUsername());
        if (client1!=null && client1.getPassword().equals(client.getPassword())){
            return client;
        }
        return null;
    }

    public boolean delete(long id) {
        clientRepository.deleteById(id);
        return true;
    }
    public boolean deleteAll() {
        clientRepository.deleteAll();
        return true;
    }
    public Client loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client1 = clientRepository.findByUsername(s);
        return client1;
    }
}