package com.portifolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portifolio.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
