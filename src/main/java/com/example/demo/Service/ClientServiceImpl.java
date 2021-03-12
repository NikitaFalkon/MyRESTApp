package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService, UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    //private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private long CLIENT_ID_HOLDER;

    public boolean create(Client client) {
        final long clientId = ++CLIENT_ID_HOLDER;
        client.setId(clientId);
        clientRepository.save(client);
        return true;
    }
    

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client read(long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean update(Client client, long id) {
        //Client client1 = clientRepository.findById(id).orElseThrow();
        Client client1 = clientRepository.findClientById(id);
        if (client1!=null)
        {
            BeanUtils.copyProperties(client, client1, "id");
            clientRepository.save(client1);
            return true;
        }
            return false;
    }

    @Override
    public boolean delete(long id) {
        clientRepository.deleteById(id);
        return true;
    }
    public boolean deleteAll() {
        clientRepository.deleteAll();
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client1 = clientRepository.findByUsername(s);
        if (client1!=null)
        {
            return client1;
        }
        return null;
    }
}