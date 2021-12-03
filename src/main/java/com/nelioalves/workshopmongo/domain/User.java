package com.nelioalves.workshopmongo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Serializable - é para converter os objetos em bytes para ser trafegado em rede ou ser gravado em arquivo.
//Essa anotação vai dizer que essa classe User corresponde a uma coleção lá no MongoDB que no caso se chama user.
@Document(collection="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Essa anotação Id fica para o atributo que for a chave.
	@Id
	//Atributos da classe User.
	private String id;
	private String name;
	private String email;
	
	//Construtor padrão.
	public User() {
	}

	//Construtor com argumentos.
	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	//Gets e Sets comuns.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//hashCode para que os objetos possam ter comparação, apenas para o atributo id.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	//equals para que os objetos possam ter comparação, apenas para o atributo id.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
