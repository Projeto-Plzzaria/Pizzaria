package com.Pizzaria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.ComidaController;
import com.pizzaria.dto.ComidaDTO;
import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Tamanho;
import com.pizzaria.repository.ComidaRepository;
import com.pizzaria.service.ComidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComidaTeste {


    private MockMvc mockMvc;
    @InjectMocks
    private ComidaController Controller;

    @Mock
    private ComidaService Service;
    @Mock
    private ComidaRepository Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(Controller).build();
    }

    @Test
    public void testLista() throws Exception {
        when(Service.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Comida/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testCadastrarSuccess() throws Exception {
        ComidaDTO DTO = new ComidaDTO();
        when(Service.cadastrar(any(Comida.class)))
                .thenReturn(new Comida());
        mockMvc.perform(post("/api/Comida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(DTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    public void testComida() {
        Long id = 1L;
        Comida comida = new Comida();
        comida.setId(id);
        comida.setTamanho(Tamanho.GIGANTE);

        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        ingredientes.add("Queijo");
        comida.setIngredientes(ingredientes);

        assertEquals(id, comida.getId());
        assertEquals(Tamanho.GIGANTE, comida.getTamanho());
        assertEquals(ingredientes, comida.getIngredientes());
    }

    @Test
    public void testListaAtivo() {
        boolean ativo = true;
        List<Comida> Ativas = new ArrayList<>();
        when(Repository.findByAtivo(ativo)).thenReturn(Ativas);
        ResponseEntity<List<ComidaDTO>> response = Controller.listaAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Ativas.size(), response.getBody().size());
    }

    @Test
    public void testDeleteExistente() {
        Long id = 1L;

        when(Repository.findById(id)).thenReturn(Optional.of(new Comida()));
        ResponseEntity<String> response = Controller.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(Repository, times(1)).deleteById(id);
    }

    @Test
    public void testAtualizarComSucesso() {
        Long id = 1L;

        when(Service.atualizar(eq(id), any())).thenReturn(new Comida());

        ComidaDTO aDTO = new ComidaDTO();
        ResponseEntity<?> response = Controller.atualizar(id, aDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }


    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}