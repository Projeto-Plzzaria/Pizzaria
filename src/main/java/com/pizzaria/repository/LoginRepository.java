package com.pizzaria.repository;

import com.pizzaria.Security.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
   UserDetails fingByNome(String nome);

}
