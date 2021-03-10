package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    //private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    public boolean create(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientId);
        clientRepository.save(client);
        return true;
    }
    

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client read(int id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean update(Client client, int id) {
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
    public boolean delete(int id) {
        clientRepository.deleteById(id);
        return true;
    }
}