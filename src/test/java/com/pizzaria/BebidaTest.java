package com.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.BebidaController;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.Endereco;
import com.pizzaria.entity.TamanhoB;
import com.pizzaria.repository.BebidasRepository;
import com.pizzaria.service.BebidaService;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

class BebidaTest {

    private MockMvc mockMvc;
    @InjectMocks
    private BebidaController bebidaController;

    @MockBean
    private BebidaService bebidaService;
    @MockBean
    private BebidasRepository bebidasRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bebidaController).build();
    }

    @Test
    void testLista() throws Exception {
        when(bebidaService.listartudo()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/Bebida/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void testCadastrarSuccess() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        when(bebidaService.cadastrar(any(Bebida.class)))
                .thenReturn(new Bebida(TamanhoB.L_1, "Laranja"));

        mockMvc.perform(post("/api/Bebida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        when(bebidaService.cadastrar(any(Bebida.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/Bebida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        when(bebidaService.cadastrar(any(Bebida.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/Bebida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }


    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Bebida bebida = new Bebida(TamanhoB.L_1, "Laranja");
        bebida.setId(id);
        bebida.setTamanho(TamanhoB.L_1);
        bebida.setSabor("Cola");
        when(bebidasRepository.findById(id))
                .thenReturn(Optional.of(bebida));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Bebida/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tamanho").value("L_1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sabor").value("Cola"));
    }

    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(bebidasRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Bebida/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



    @Test
    void testListaAtivo() {
        boolean ativo = true;
        List<Bebida> bebidasAtivas = new ArrayList<>();
        when(bebidasRepository.findByAtivo(ativo)).thenReturn(bebidasAtivas);
        ResponseEntity<List<BebidaDTO>> response = bebidaController.listaAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bebidasAtivas.size(), response.getBody().size());
    }

    @Test
    void testDeleteExistente() {
        Long id = 1L;

        when(bebidasRepository.findById(id)).thenReturn(Optional.of(new Bebida(TamanhoB.L_1, "Laranja")));

        ResponseEntity<String> response = bebidaController.delete(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(bebidasRepository, times(1)).deleteById(id);
    }
    @Test
    public void testDeleteIdNaoEncontrado() throws Exception {
        Long id = 2L;

        when(bebidasRepository.findById(id))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/Bebida/delete/{id}", id))
                .andExpect(status().isNotFound());

        verify(bebidasRepository, times(1)).findById(id);
        verify(bebidasRepository, never()).deleteById(id);
    }


    @Test
    void testAtualizarComSucesso() {

        Long id = 1L;

        when(bebidaService.atualizar(eq(id), any())).thenReturn(new Bebida(TamanhoB.L_1, "Laranja"));
        BebidaDTO bebidaDTO = new BebidaDTO();
        ResponseEntity<?> response = bebidaController.atualizar(id, bebidaDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        BebidaDTO bebidaDTO = new BebidaDTO();

        when(bebidaService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = bebidaController.atualizar(id, bebidaDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }


    @Test
    void testTamanhoGetterSetter() {
        Bebida bebida = new Bebida(TamanhoB.L_1, "Laranja");
        TamanhoB tamanho = TamanhoB.L_1;
        bebida.setTamanho(tamanho);
        assertEquals(tamanho, bebida.getTamanho());
    }

    @Test
    void testSaborGetterSetter() {
        Bebida bebida = new Bebida(TamanhoB.L_1, "Laranja");
        String sabor = "Cola";
        bebida.setSabor(sabor);
        assertEquals(sabor, bebida.getSabor());
    }


    @Test
    void testConstructor() {
        Bebida bebida = new Bebida();
        assertNotNull(bebida);
    }
    @Test
    void testConverterListaDeBebidasParaListaDeBebidaDTOs() {
        Bebida bebida1 = new Bebida(TamanhoB.L_2, "Laranja");
        Bebida bebida2 = new Bebida(TamanhoB.L_2, "Limão");

        List<Bebida> bebidas = new ArrayList<>();
        bebidas.add(bebida1);
        bebidas.add(bebida2);

        List<BebidaDTO> bebidaDTOs = BebidaDTO.toDtoList(bebidas);

        assertNotNull(bebidaDTOs);
        assertEquals(2, bebidaDTOs.size());

        assertEquals(bebida1.getTamanho(), bebidaDTOs.get(0).getTamanho());
        assertEquals(bebida1.getSabor(), bebidaDTOs.get(0).getSabor());

        assertEquals(bebida2.getTamanho(), bebidaDTOs.get(1).getTamanho());
        assertEquals(bebida2.getSabor(), bebidaDTOs.get(1).getSabor());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}