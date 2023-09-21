package com.pizzaria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.ComidaController;
import com.pizzaria.dto.BebidaDTO;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ComidaTest {


    private MockMvc mockMvc;
    @InjectMocks
    private ComidaController Controller;

    @Mock
    private ComidaService Service;
    @Mock
    private ComidaRepository Repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(Controller).build();
    }

    @Test
    void testLista() throws Exception {
        when(Service.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Comida/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void testCadastrarSuccess() throws Exception {
        ComidaDTO DTO = new ComidaDTO();
        when(Service.cadastrar(any(Comida.class)))
                .thenReturn(new Comida(Tamanho.GIGANTE, "Calabresa"));
        mockMvc.perform(post("/api/Comida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(DTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    /////////
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        ComidaDTO bebidaDTO = new ComidaDTO();
        when(Service.cadastrar(any(Comida.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/Comida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        ComidaDTO bebidaDTO = new ComidaDTO();
        when(Service.cadastrar(any(Comida.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/Comida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    ////////


    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(Repository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Comida/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    //////
    @Test
    void testComida() {
        Long id = 1L;
        Comida comida = new Comida(Tamanho.GIGANTE, "Calabresa");
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
    void testListaAtivo() {
        boolean ativo = true;
        List<Comida> Ativas = new ArrayList<>();
        when(Repository.findByAtivo(ativo)).thenReturn(Ativas);
        ResponseEntity<List<ComidaDTO>> response = Controller.listaAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Ativas.size(), response.getBody().size());
    }


    @Test
    void testDeleteExistente() {
        Long id = 1L;

        when(Repository.findById(id)).thenReturn(Optional.of(new Comida(Tamanho.GIGANTE, "Calabresa")));
        ResponseEntity<String> response = Controller.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(Repository, times(1)).deleteById(id);
    }
    ///
    @Test
    void testDeleteIdNaoEncontrado() throws Exception {
        Long id = 2L;

        when(Repository.findById(id))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/Comida/delete/{id}", id))
                .andExpect(status().isNotFound());

        verify(Repository, times(1)).findById(id);
        verify(Repository, never()).deleteById(id);
    }
////


    @Test
    void testAtualizarComSucesso() {
        Long id = 1L;

        when(Service.atualizar(eq(id), any())).thenReturn(new Comida(Tamanho.GIGANTE, "Calabresa"));

        ComidaDTO aDTO = new ComidaDTO();
        ResponseEntity<?> response = Controller.atualizar(id, aDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }////
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        ComidaDTO bebidaDTO = new ComidaDTO();

        when(Service.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = Controller.atualizar(id, bebidaDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }

    ///

    @Test
    void testTamanhoGetterSetter() {
        Comida comida = new Comida(Tamanho.GIGANTE, "Calabresa");
        Tamanho tamanho = Tamanho.GIGANTE;
        comida.setTamanho(tamanho);
        assertEquals(tamanho, comida.getTamanho());
    }

    @Test
    void testIngredientesGetterSetter() {
        Comida comida = new Comida(Tamanho.GIGANTE, "Calabresa");
        List<String> ingredientes = Collections.singletonList("Cola");
        comida.setIngredientes(ingredientes);
        assertEquals(ingredientes, comida.getIngredientes());
    }

    @Test
    void testConverterListaDeComidasParaListaDeComidaDTOs() {
        Comida comida1 = new Comida("Média", Arrays.asList("Queijo", "Presunto"));
        Comida comida2 = new Comida("Grande", Arrays.asList("Pepperoni", "Cogumelos"));
        List<Comida> comidas = Arrays.asList(comida1, comida2);
        List<ComidaDTO> comidaDTOs = ComidaDTO.toDtoList(comidas);
        assertNotNull(comidaDTOs);
        assertEquals(2, comidaDTOs.size());

        assertEquals(comida1.getTamanho(), comidaDTOs.get(0).getTamanho());
        assertEquals(comida1.getIngredientes(), comidaDTOs.get(0).getIngredientes());
        assertEquals(comida2.getTamanho(), comidaDTOs.get(1).getTamanho());
        assertEquals(comida2.getIngredientes(), comidaDTOs.get(1).getIngredientes());
    }


    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}