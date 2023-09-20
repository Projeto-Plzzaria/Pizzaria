package com.Pizzaria.pizzaria;

import com.Pizzaria.pizzaria.Controller.ComidaController;
import com.Pizzaria.pizzaria.Controller.EnderecoController;
import com.Pizzaria.pizzaria.DTO.ComidaDTO;
import com.Pizzaria.pizzaria.DTO.EnderecoDTO;
import com.Pizzaria.pizzaria.Entity.Comida;
import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Entity.Tamanho;
import com.Pizzaria.pizzaria.Repository.ComidaRepository;
import com.Pizzaria.pizzaria.Repository.EnderecoRepository;
import com.Pizzaria.pizzaria.Service.ComidaService;
import com.Pizzaria.pizzaria.Service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnderecoTeste {

    private MockMvc mockMvc;
    @InjectMocks
    private EnderecoController Controller;

    @Mock
    private EnderecoService Service;
    @Mock
    private EnderecoRepository Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(Controller).build();
    }

    @Test
    public void testLista() throws Exception {
        when(Service.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Endereco/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testCadastrarSuccess() throws Exception {
        EnderecoDTO DTO = new EnderecoDTO();
        when(Service.cadastrar(any(Endereco.class)))
                .thenReturn(new Endereco());

        mockMvc.perform(post("/api/Endereco/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(DTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }


    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
