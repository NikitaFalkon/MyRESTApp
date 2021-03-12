package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Service.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.NullPointerException;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    ObjectMapper objectMapper;
/*    @AfterEach
    public void resetDb() {
        clientService.deleteAll();
    }*/
    private Client createTestPerson(String name) {
        Client client = new Client();
        client.setName(name);
        clientService.create(client);
        return client;
    }
    @Test
    public void FindingTest() throws Exception {
        //Client client = createTestPerson("Michail");
        this.mockMvc.perform(get("/clients"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").isArray())
                .andExpect(jsonPath(".username").value("Michail"));
    }
    @Test
    public void FindingClientTest() throws Exception {
        long id = 58;
        mockMvc.perform(get("/clients/{id}",id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(58))
                .andExpect(jsonPath(".username").value("Michail"));
    }
    /*@Test
    public void FindingNullClientTest() throws Exception {
        mockMvc.perform(
                get("/persons/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NullPointerException));
    }*/
    @Test
    public void DeletingClientTest() throws Exception {
        Client person = createTestPerson("Nick");
        long id = 59;
        mockMvc.perform(delete("/clients/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}