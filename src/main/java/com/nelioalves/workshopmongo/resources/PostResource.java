package com.nelioalves.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.services.PostService;

//Lembrando que os resources são os controladores/recursos REST.

//Anotação para dizer que essa classe é um recurso REST.
@RestController
//Anotação para dizer qual vai ser o endpoint, no caso /posts.
@RequestMapping(value="/posts")
public class PostResource {

	//Anotação para injetar um serviço/service.
	@Autowired
	private PostService service;
	
	//Essa anotação está dizendo que meu método vai ter o caminho /posts e o valor de um id.
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	//Essa anotação está dizendo que meu método vai encontrar um post por uma String.
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	//A anotação @RequestParam é porque esse endpoint vai ser uma parâmetro.
	//value="text", defaultValue="" se o valor "text", não for informado no lugar vai ficar a String vazia.
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		//Essa linha decodifica o texto.
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);	
	}
	
}
