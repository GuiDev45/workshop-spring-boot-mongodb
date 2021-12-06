package com.nelioalves.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

//Lembrando que os resources são os controladores/recursos REST.

//Anotação para dizer que essa classe é um recurso REST.
@RestController
//Anotação para dizer qual vai ser o endpoint, no caso /users.
@RequestMapping(value="/users")
public class UserResource {

	//Anotação para injetar um serviço/service.
	@Autowired
	private UserService service;
	
	//Anotação para dizer que esse método vai ser o endpoint REST no caminho /users.
	//Lembrando que o Get é para obter informações no padrão REST.
	@GetMapping
	//Método que retorna um objeto sofisticado ResponseEntity, esse objeto consegue encapsular toda uma estrutura necessária, 
	//para retornar respostas http já com possíveis cabeçalhos e possíveis erros, etc.
	public ResponseEntity<List<UserDTO>> findAll() {
		//Dessa forma busca os usuários no banco de dados MongoDB e guardar na lista/list.
		List<User> list = service.findAll();
		//Convertendo uma lista de User para uma lista de UserDTO.
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		//.ok é o que vai instanciar o ResponseEntity já com o campo de resposta http, que a resposta ocorreu com sucesso.
		//.body é para definir qual vai ser o corpo da resposta, que no caso vai ser a lista/listDto.
		return ResponseEntity.ok().body(listDto);	
		}
}
