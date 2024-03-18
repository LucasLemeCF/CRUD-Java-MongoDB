package com.example.javacrud.controller;

import com.example.javacrud.dto.LivroDTO;
import com.example.javacrud.model.LivroModel;
import com.example.javacrud.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<LivroModel> createLivro(@Valid @RequestBody LivroDTO livroDTO) {
        var livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDTO, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LivroModel>> findAllLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findLivroById(@PathVariable(value="id") String id) {
        Optional<LivroModel> livro = Optional.ofNullable(livroRepository.findBookById(id));
        if(livro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLivro(@PathVariable(value="id") String id, @Valid @RequestBody LivroDTO livroDTO) {
        Optional<LivroModel> livro = Optional.ofNullable(livroRepository.findBookById(id));
        if(livro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
        var livroModel = livro.get();
        BeanUtils.copyProperties(livroDTO, livroModel);
        livroModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLivro(@PathVariable(value="id") String id) {
        Optional<LivroModel> livro = Optional.ofNullable(livroRepository.findBookById(id));
        if(livro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
        livroRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}