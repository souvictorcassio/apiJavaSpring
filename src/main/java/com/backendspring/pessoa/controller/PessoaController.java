package com.backendspring.pessoa.controller;

import com.backendspring.pessoa.dto.PessoaDto;
import com.backendspring.pessoa.model.Pessoa;
import com.backendspring.pessoa.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto pessoaDto){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        Pessoa savedPessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable UUID id){
        Optional<Pessoa> foundPessoa = pessoaRepository.findById(id);
        if(foundPessoa.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found! :(");

        }

        return ResponseEntity.status(HttpStatus.OK).body(foundPessoa.get());
    }

}
