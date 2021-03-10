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
        client.setName("name");
        boolean Created = clientService.create(client);
        Assert.assertTrue(Created);
        Assert.assertNotNull(client.getName());
    }
    @Test
    public void testFailCreate() {
        Client client = new Client();
        client.setName("n");
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findByName("u");
        boolean Created = clientService.create(client);
        Assert.assertFalse(Created);
    }
    @Test
    public void testUpdate(){
        Client client = new Client();
        client.setName("name");
        client.setId(2);
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(2);
        boolean Updated = clientService.update(client, client.getId());
        Assert.assertTrue(Updated);
        Assert.assertEquals(client.getName(), "name");
    }
    @Test
    public void testFailUpdate(){
        Client client = new Client();
        client.setName("name");
        client.setId(2);
        /*Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(3);*/
        boolean Updated = clientService.update(client, client.getId());
        Assert.assertFalse(Updated);
    }
    @Test
    public void testDelete(){
        Client client = new Client();
        client.setName("name");
        client.setId(2);
        Mockito.doReturn(new Client())
                .when(clientRepository)
                .findClientById(2);
        boolean Deleted = clientService.delete(client.getId());
        Assert.assertTrue(Deleted);
    }


}