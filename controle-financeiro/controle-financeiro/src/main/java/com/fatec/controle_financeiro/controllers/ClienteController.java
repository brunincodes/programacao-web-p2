package com.fatec.controle_financeiro.controllers;

/* import java.util.ArrayList; */
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

import com.fatec.controle_financeiro.domain.cliente.ClienteRepository;
import com.fatec.controle_financeiro.entities.Cliente;

@RequestMapping("/api/Cliente")
@RestController


public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){

        Cliente clienteCreated = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente(){

        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getByIdCliente(@PathVariable int id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente entity){
        Optional<Cliente> clienteAtual = clienteRepository.findById(id);
        if(clienteAtual.isPresent()){
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
