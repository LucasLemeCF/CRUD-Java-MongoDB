package com.example.javacrud.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "livros")
public class LivroModel {
    @Id
    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer numeroPaginas;
}
