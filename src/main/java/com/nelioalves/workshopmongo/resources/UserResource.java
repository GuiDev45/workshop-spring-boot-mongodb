package com.nelioalves.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;

//Lembrando que os resources são os controladores/recursos REST.

//Anotação para dizer que essa classe é um recurso REST.
@RestController
//Anotação para dizer qual vai ser o endpoint, no caso /users.
@RequestMapping(value="/users")
public class UserResource {

	//Anotação para dizer que esse método vai ser o endpoint REST no caminho /users.
	//Lembrando que o Get é para obter informações no padrão REST.
	@GetMapping
	//Método que retorna um objeto sofisticado ResponseEntity, esse objeto consegue encapsular toda uma estrutura necessária, 
	//para retornar respostas http já com possíveis cabeçalhos e possíveis erros, etc.
	public ResponseEntity<List<User>> findAll() {
		//Teste para saber se está instanciando corretamente os usuários.
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		//Adicionando os usuários a lista.
		list.addAll(Arrays.asList(maria, alex));
		//.ok é o que vai instanciar o ResponseEntity já com o campo de resposta http, que a resposta ocorreu com sucesso.
		//.body é para definir qual vai ser o corpo da resposta, que no caso vai ser a lista/list.
		return ResponseEntity.ok().body(list);	}
}
