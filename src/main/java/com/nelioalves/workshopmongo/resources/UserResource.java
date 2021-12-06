package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	//Essa anotação está dizendo que meu método vai ter o caminho /users e o valor de um id.
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));	
	}
	
	//Anotação para dizer que esse método vai ser o endpoint REST para inserir um novo usuário.
	@PostMapping
	//Para que o endpoint aceite este objeto é necessário colocar a anotação @RequestBody.
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//Nessa reposta a URI, é um cabeçalho com a URL do novo recurso criado,
		//Essa URI é um código específico do Spring que faz exatamente isso.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Anotação para dizer que esse método vai ser o endpoint REST para deletar um usuário.
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		//Essa reposta não precisa retornar nada, então a resposta é o código 204 e esse código no ResponseEntity é o .noContent()
		return ResponseEntity.noContent().build();
	}
	
	//Anotação para dizer que esse método vai ser o endpoint REST para atualizar um usuário.
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
