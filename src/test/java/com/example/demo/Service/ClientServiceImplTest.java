package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceImplTest extends TestCase {
    @Autowired
    ClientServiceImpl clientService;
    @MockBean
    ClientRepository clientRepository;
    @Test
    public void testCreate() {
        Client client = new Client();
        client.setUsername("name");
        boolean Created = clientService.create(client);
        Assert.assertTrue(Created);
        Assert.assertNotNull(client.getUsername());
    }
    @Test
    public void testFailCreate() {
        Client client = new Client();
        client.setUsername("n");
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findByUsername("u");
        boolean Created = clientService.create(client);
        Assert.assertFalse(Created);
    }
    @Test
    public void testUpdate(){
        Client client = new Client();
        client.setUsername("name");
        client.setId((long) 2);
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(2);
        boolean Updated = clientService.update(client, client.getId());
        Assert.assertTrue(Updated);
        Assert.assertEquals(client.getUsername(), "name");
    }
    @Test
    public void testFailUpdate(){
        Client client = new Client();
        client.setUsername("name");
        client.setId((long) 2);
        /*Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(3);*/
        boolean Updated = clientService.update(client, client.getId());
        Assert.assertFalse(Updated);
    }
    @Test
    public void testDelete(){
        Client client = new Client();
        client.setUsername("name");
        client.setId((long) 2);
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(2);
        boolean Deleted = clientService.delete(client.getId());
        Assert.assertTrue(Deleted);
    }
    @Test
    public void testloadUserByUsername(){
        Client client = new Client();
        client.setUsername("name");
        client.setId((long) 2);
        Mockito.doReturn(client)
                .when(clientRepository)
                .findByUsername("name");
        Client Find =  (Client) clientService.loadUserByUsername("name");
        Assert.assertNotNull(Find.getUsername());
        Assert.assertEquals(Find.getUsername(), client.getUsername());
    }


}