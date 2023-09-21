package com.pizzaria;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.ClienteController;
import com.pizzaria.controller.FuncionarioController;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.entity.Cargo;
import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Funcionario;
import com.pizzaria.entity.Pessoa;
import com.pizzaria.repository.ClienteRepository;
import com.pizzaria.repository.FuncionarioRepository;
import com.pizzaria.service.ClienteService;
import com.pizzaria.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class ClienteTest {


    private MockMvc mockMvc;
    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testCliente() throws Exception {
        when(clienteService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Cliente/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCadastrarSuccess() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();


        when(clienteService.cadastrar(any(Cliente.class)))
                .thenReturn(new Cliente());

        mockMvc.perform(post("/api/Cliente/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }

    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Cliente/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteExistente() {
        Long id = 1L;

        when(clienteRepository.findById(id)).thenReturn(Optional.of(new Cliente()));
        ResponseEntity<String> response = clienteController.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(clienteRepository, times(1)).deleteById(id);
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
