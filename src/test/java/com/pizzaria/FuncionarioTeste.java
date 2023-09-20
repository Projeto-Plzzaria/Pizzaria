package com.pizzaria;

import com.pizzaria.controller.FuncionarioController;
import com.pizzaria.repository.FuncionarioRepository;
import com.pizzaria.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@WebAppConfiguration
public class FuncionarioTeste {

    private MockMvc mockMvc;
    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }














}
