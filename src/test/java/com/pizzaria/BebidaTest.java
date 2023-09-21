package com.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.BebidaController;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.TamanhoB;
import com.pizzaria.repository.BebidasRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.pizzaria.service.BebidaService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

class BebidaTest {

    private MockMvc mockMvc;
    @InjectMocks
    private BebidaController bebidaController;

    @Mock
    private BebidaService bebidaService;
    @Mock
    private BebidasRepository bebidasRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bebidaController).build();
    }

    @Test
    void testLista() throws Exception {
        // Configure o comportamento do serviço mock
        when(bebidaService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Bebida/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // Adicione mais verificações conforme necessário para garantir que o método lista() esteja funcionando corretamente
    }
    @Test
    void testGetterAndSetter() {
        Bebida bebida = new Bebida();

        // Teste do setter
        bebida.setSabor("Coca-Cola");
        assertThat(bebida.getSabor(), is("Coca-Cola"));

        // Adicione testes semelhantes para outros getters e setters
    }












    @Test
    void testCadastrarSuccess() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        when(bebidaService.cadastrar(any(Bebida.class)))
                .thenReturn(new Bebida());

        mockMvc.perform(post("/api/Bebida/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Bebida bebida = new Bebida();
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

        when(bebidasRepository.findById(id)).thenReturn(Optional.of(new Bebida()));

        ResponseEntity<String> response = bebidaController.delete(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(bebidasRepository, times(1)).deleteById(id);
    }

    @Test
    void testAtualizarComSucesso() {

        Long id = 1L;

        when(bebidaService.atualizar(eq(id), any())).thenReturn(new Bebida());

        BebidaDTO bebidaDTO = new BebidaDTO();

        ResponseEntity<?> response = bebidaController.atualizar(id, bebidaDTO);

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