package com.example.javacrud.repositories;

import com.example.javacrud.model.LivroModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LivroRepository extends MongoRepository<LivroModel, String> {

    @Query("{id:'?0'}")
    LivroModel findBookById(String id);

}
