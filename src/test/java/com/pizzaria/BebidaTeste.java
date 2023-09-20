package com.pizzaria;

import com.pizzaria.controller.BebidaController;
import com.pizzaria.entity.Bebida;
import com.pizzaria.repository.BebidasRepository;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.TamanhoB;
import com.pizzaria.service.BebidaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebAppConfiguration
public class BebidaTeste {

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
    public void testLista() throws Exception {
        when(bebidaService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Bebida/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testCadastrarSuccess() throws Exception {
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
    public void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Bebida bebida = new Bebida();
        bebida.setId(id);
        bebida.setTamanho(TamanhoB._1L);
        bebida.setSabor("Cola");

        when(bebidasRepository.findById(id))
                .thenReturn(Optional.of(bebida));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Bebida/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tamanho").value("_1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sabor").value("Cola"));
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
