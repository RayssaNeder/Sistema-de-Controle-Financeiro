package com.xpto.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.CreateClientePessoaFisicaDTO;
import com.xpto.demo.dto.CreateClientePessoaJuridicaDTO;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;
import com.xpto.demo.service.ClienteService;
import com.xpto.demo.swagger.ClienteApi;



@RestController
@RequestMapping("/cliente")
public class ClienteController implements ClienteApi{
	private final ClienteService clienteService;

	  public ClienteController(ClienteService clienteService) {
	    this.clienteService = clienteService;
	  }

	 @PostMapping("/pessoa-fisica/novo")
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<Cliente> createCliente(@RequestBody CreateClientePessoaFisicaDTO createcliente) {
	    return new ResponseEntity<>(clienteService.create(createcliente), HttpStatus.CREATED);
	  }
	 
	 @PostMapping("/pessoa-juridica/novo")
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<Cliente> createCliente(@RequestBody CreateClientePessoaJuridicaDTO createcliente) {
	    return new ResponseEntity<>(clienteService.create(createcliente), HttpStatus.CREATED);
	  }

	    @GetMapping("/{uuid}")
	  public ResponseEntity<Cliente> readCliente(@PathVariable UUID uuid) {
	    return new ResponseEntity<>(clienteService.read(uuid), HttpStatus.OK);
	  }

	  @PutMapping("/{uuid}")
	  public ResponseEntity<Cliente> updateCliente(@PathVariable UUID uuid, @RequestBody UpdateCliente updatecliente) {
	    return new ResponseEntity<>(clienteService.update(uuid, updatecliente), HttpStatus.OK);
	  }

	  @DeleteMapping("/{uuid}")
	  public ResponseEntity<Void> deleteCliente(@PathVariable UUID uuid) {
	    clienteService.delete(uuid);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	    @GetMapping
	  public ResponseEntity<PageCliente> listCliente(
			  @RequestParam(defaultValue = "0") Integer page,
			    @RequestParam(defaultValue = "10") Integer size,
			    @RequestParam(required = false) List<String> sort) {
	    return new ResponseEntity<>(clienteService.list(page, size, sort), HttpStatus.OK);
	  }

	}
