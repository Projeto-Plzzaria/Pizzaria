package com.pizzaria.Security;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table(name = "login",schema = "public")
public class Login implements UserDetails {
    @Id
    @Column(name = "id",nullable=false,unique=true)
    private long id;
    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name = "senha",nullable = false)
    private String senha;
    @Column(name = "autorizacao",nullable = false)
    private UserRole autorizacao;

    public Login(String nome,String senha,UserRole autorizacao){
        this.nome = nome;
        this.senha = senha;
        this.autorizacao = autorizacao;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.autorizacao == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
