package com.pizzaria.controller;

import com.pizzaria.Security.AuthenticationDTO;
import com.pizzaria.Security.Login;
import com.pizzaria.Security.RegisterDTO;
import com.pizzaria.repository.LoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authetication;
    @Autowired
    private LoginRepository repository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.nome(), data.senha());
        var auth = this.authetication.authenticate(usuarioSenha);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegisterDTO data ){
        if(this.repository.fingByNome(data.nome())!= null) return  ResponseEntity.badRequest().build();
        String encrypted = new BCryptPasswordEncoder().encode(data.senha());
        Login newLogin = new Login(data.nome(),encrypted,data.role());

        this.repository.save(newLogin);
        return ResponseEntity.ok().build();
    }
}
