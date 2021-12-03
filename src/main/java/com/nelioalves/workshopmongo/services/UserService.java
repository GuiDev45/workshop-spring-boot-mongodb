package com.nelioalves.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;

//Service é o serviço responsável para trabalhar com os usuários.

//Essa anotação é para dizer que essa classe vai ser um serviço que pode ser injetado em outras classes.
@Service
public class UserService {
	
	//Como o serviço/service tem que conversar com o repositorio/repository é usada essa anotação.
	//Com essa anotação o próprio Spring vai achar a definição desse objeto que é o repository e vai instanciar.
	//Em resumo, injeção de dependência do Spring.
	@Autowired
	private UserRepository repo;
	
	//Esse List é um método responsável por retornar todos os usuários do banco de dados.
	public List<User> findAll() {
		return repo.findAll();
	}

}
