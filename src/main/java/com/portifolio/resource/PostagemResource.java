package com.portifolio.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portifolio.model.Postagem;
import com.portifolio.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
public class PostagemResource {

	@Autowired
	PostagemRepository postagemRepository;
	
	@GetMapping
	public List<Postagem> nome(){
		return postagemRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Postagem> criar(@Validated @RequestBody Postagem postagem, HttpServletResponse response) {
		Postagem postagemSalva = postagemRepository.save(postagem);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(postagemSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(postagemSalva); //retorna no header a localização da uri
	}
}
