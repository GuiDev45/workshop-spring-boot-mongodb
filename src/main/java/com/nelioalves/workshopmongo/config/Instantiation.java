package com.nelioalves.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;

//Carga inicial da base de dados para fazer teste.

//Essa anotação é para o Spring entender que essa classe é uma configuração.
@Configuration
//Essa classe implementa a interface CommandLineRunner
public class Instantiation implements CommandLineRunner {

	//Esse UserRepository é para fazer operações com o banco de dados.
	@Autowired
	private UserRepository userRepository;
	
	//Esse método instância os objetos e salva no banco de dados.
	@Override
	public void run(String... args) throws Exception {
		
		//.deleteAll essa operação vai limpar a coleção lá no MongoDB.
		userRepository.deleteAll();
		
		//Depois esses objetos vão ser instanciados.
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//.saveAll os objetos instanciados vão ser salvos lá na coleção de user do MongoDB.
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
	}

}
