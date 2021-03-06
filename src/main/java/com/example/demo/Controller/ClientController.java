package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping(value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Client client)  throws ParseException
    {
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read()
    {
        List<Client> clients = clientService.readAll();
        return clients !=null && !clients.isEmpty()
                ?new ResponseEntity<>(clients, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable ("id") int id)
    {
        Client client = clientService.read(id);
        return client !=null
                ?new ResponseEntity<>(client, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> put(@PathVariable ("id") int id, @RequestBody Client client)
    {
        boolean update = clientService.update(client, id);
        return update
                ?new ResponseEntity<>(HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id)
    {
        boolean delete = clientService.delete(id);
        return delete
                ?new ResponseEntity<>(HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
}