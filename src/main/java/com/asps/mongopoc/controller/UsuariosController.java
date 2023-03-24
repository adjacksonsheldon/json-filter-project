package com.asps.mongopoc.controller;

import com.asps.mongopoc.entity.Usuario;
import com.asps.mongopoc.repository.UsuarioRepository;
import com.asps.mongopoc.service.UsuarioService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @GetMapping
    public MappingJacksonValue consultarTodos(@RequestParam(required = false) String campos){
        List<Usuario> usuarios = usuarioRepository.findAll();
        MappingJacksonValue wrapper = new MappingJacksonValue(usuarios);
        SimpleFilterProvider filterProvider = getSimpleFilterProvider(campos);
        wrapper.setFilters(filterProvider);

        return wrapper;
    }

    private SimpleFilterProvider getSimpleFilterProvider(String campos) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("usuariosFilter", SimpleBeanPropertyFilter.serializeAll());

        if(isNotBlank(campos)) {
            filterProvider.addFilter("usuariosFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }
        return filterProvider;
    }

    @PostMapping
    public Usuario incluir(@RequestBody Usuario usuario){
        return usuarioService.incluirOuAtualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") String cpf){
        final var usuarioOptional = usuarioRepository.findById(cpf);

        usuarioOptional.ifPresent(usuarioRepository::delete);
    }

    @GetMapping("/{usuarioId}")
    public Usuario consultarUsuarioPorId(@PathVariable("usuarioId") String usuarioId){
        return usuarioService.consultarPorId(usuarioId);
    }
}