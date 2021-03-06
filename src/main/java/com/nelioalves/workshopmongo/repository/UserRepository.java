package com.nelioalves.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.User;

@Repository
//Essa interface vai herdar a interface MongoRepository que já tem no Spring data,
//User é o tipo da classe de domínio que ele vai gerenciar, 
//String é o tipo do id dessa classe.
public interface UserRepository extends MongoRepository<User, String> {

}
