package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

//Carga inicial da base de dados para fazer teste.

//Essa anotação é para o Spring entender que essa classe é uma configuração.
@Configuration
//Essa classe implementa a interface CommandLineRunner
public class Instantiation implements CommandLineRunner {

	//Esse UserRepository é para fazer operações com o banco de dados.
	@Autowired
	private UserRepository userRepository;
	
	//Injetando também o PostRepository lá no banco de dados/salvando os posts no banco de dados.
	@Autowired
	private PostRepository postRepository;
	
	//Esse método instância os objetos e salva no banco de dados.
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//.deleteAll essa operação vai limpar a coleção lá no MongoDB.
		userRepository.deleteAll();
		
		//.deleteAll essa operação vai limpar a coleção lá no MongoDB, vai ser feito com os posts também.
		postRepository.deleteAll();
		
		//Depois esses objetos vão ser instanciados.
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);
		
		//.saveAll os objetos instanciados vão ser salvos lá na coleção de user do MongoDB.
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		//.saveAll os objetos instanciados vão ser salvos lá na coleção de user do MongoDB.
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
