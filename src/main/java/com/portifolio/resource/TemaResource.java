package com.portifolio.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portifolio.model.Tema;
import com.portifolio.repository.TemaRepository;

@RestController //encontrar a classe retorno facilitado, converte para json
@RequestMapping("/tema") //mapeando a requisição
public class TemaResource {
	
	@Autowired //injetando a dependencia da interface,como se tivesse dado o new
	private TemaRepository temaRepository;
	
	@GetMapping
	public List<Tema> listar(){
		return temaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Tema> criar(@Validated @RequestBody Tema tema, HttpServletResponse response) {
		Tema temaSalvo = temaRepository.save(tema);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(temaSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(temaSalvo);// retornar o codigo 201 created, caso contrario irá mostrar 200 ok ou @responseStatus
	}
	
	@GetMapping("/{id}")
	public Tema buscarPoId(@PathVariable Long id) {
		return temaRepository.findById(id).orElse(null);
	}
	
}

