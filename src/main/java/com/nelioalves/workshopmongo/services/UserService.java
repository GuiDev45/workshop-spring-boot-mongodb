package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

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

	//Método para achar um usuário por id e caso ele não exista, é lançado um erro customizado.
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	//Método para inserir/POST.
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//Método para deletar/DELETE
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	//Método que pega um DTO e instância um usuário.
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
