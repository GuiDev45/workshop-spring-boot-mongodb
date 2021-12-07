package com.nelioalves.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

//Service é o serviço responsável para trabalhar com os posts.

//Essa anotação é para dizer que essa classe vai ser um serviço que pode ser injetado em outras classes.
@Service
public class PostService {
	
	//Como o serviço/service tem que conversar com o repositorio/repository é usada essa anotação.
	//Com essa anotação o próprio Spring vai achar a definição desse objeto que é o repository e vai instanciar.
	//Em resumo, injeção de dependência do Spring.
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
	    Optional<Post> user = repo.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}
	
	//Esse é o método de busca de um post usando uma String.
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	//Método de consulta de data min e data max.
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
}
