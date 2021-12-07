package com.nelioalves.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

@Repository
//Essa interface vai herdar a interface MongoRepository que já tem no Spring data,
//Post é o tipo da classe de domínio que ele vai gerenciar, 
//String é o tipo do id dessa classe.
public interface PostRepository extends MongoRepository<Post, String> {

	//Essa anotação é uma expressão regular de consultas do MongoDB.
	@Query("{ 'title': { $regex: ?0/pattern/, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//Essa linha faz com que o Spring data monte a consulta baseado em um String.
	//A parte do IgnoreCase vai ignorar na busca letras maiúsculas e minúsculas.
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//Método que busca os posts contendo um dado String em qualquer lugar, título, corpo, comentários ou data min e data max.
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0/pattern/, $options: 'i' } }, { 'body': { $regex: ?0/pattern/, $options: 'i' } }, { 'comments.text': { $regex: ?0/pattern/, $options: 'i' } } ] }\r\n"
			+ " ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
