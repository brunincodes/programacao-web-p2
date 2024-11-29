package com.fatec.controle_financeiro.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.domain.fornecedor.FornecedorRepository;
import com.fatec.controle_financeiro.entities.Fornecedor;

@RequestMapping("/api/Fornecedor")
@RestController

public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor){
        Fornecedor fornecedorCreated = fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(fornecedorCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedor(){
           List<Fornecedor> fornecedores =fornecedorRepository.findAll();
           return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> getByIdFornecedor(@PathVariable int id){
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if(fornecedor.isPresent()){
            return new ResponseEntity<>(fornecedor.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable int id, @RequestBody Fornecedor entityFornecedor){
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if(fornecedorAtual.isPresent()){
            entityFornecedor.setId(id);
            fornecedorRepository.save(entityFornecedor);
            return new ResponseEntity<>(entityFornecedor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable int id){
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if(fornecedorAtual.isPresent()){
            fornecedorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
