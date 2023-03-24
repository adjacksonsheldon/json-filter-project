package com.asps.mongopoc.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@JsonFilter("usuariosFilter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usuarios")
public class Usuario {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String cpf;
    private String nome;
    private String email;
    private String senha;
}
