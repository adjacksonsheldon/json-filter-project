package com.asps.mongopoc.repository;

import com.asps.mongopoc.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
