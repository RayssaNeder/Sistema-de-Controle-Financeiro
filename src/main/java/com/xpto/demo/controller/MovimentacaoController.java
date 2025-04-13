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

import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.CreateMovimentacaoDTO;
import com.xpto.demo.dto.PageMovimentacaoDTO;
import com.xpto.demo.dto.UpdateMovimentacao;
import com.xpto.demo.service.MovimentacaoService;
import com.xpto.demo.swagger.MovimentacaoApi;


@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController  implements MovimentacaoApi{
	private final MovimentacaoService covimentacaoService;

	  public MovimentacaoController(MovimentacaoService covimentacaoService) {
	    this.covimentacaoService = covimentacaoService;
	  }

	  @PostMapping("/novo")
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<MovimentacaoDTO> createMovimentacao(@RequestBody CreateMovimentacaoDTO createcovimentacao) {
	    return new ResponseEntity<>(covimentacaoService.create(createcovimentacao), HttpStatus.CREATED);
	  }

	  @GetMapping("/{uuid}")
	  public ResponseEntity<MovimentacaoDTO> readMovimentacao(@PathVariable UUID uuid) {
	    return new ResponseEntity<>(covimentacaoService.read(uuid), HttpStatus.OK);
	  }
	  
	  
	  @GetMapping("/get-all-by-cliente/{uuidCliente}")
	  public ResponseEntity<List<MovivemtosByClienteDTO>> readAllMovimentacoesByCliente(@PathVariable UUID uuidCliente) {
	    return new ResponseEntity<>(covimentacaoService.getAllMovimentacoesByCliente(uuidCliente), HttpStatus.OK);
	  }

	  @PutMapping("/{uuid}")
	  public ResponseEntity<MovimentacaoDTO> updateMovimentacao(@PathVariable UUID uuid, @RequestBody UpdateMovimentacao updateMovimentacao) {
	    return new ResponseEntity<>(covimentacaoService.update(uuid, updateMovimentacao), HttpStatus.OK);
	  }
	  

	  @DeleteMapping("/{uuid}")
	  public ResponseEntity<Void> deleteMovimentacao(@PathVariable UUID uuid) {
	    covimentacaoService.delete(uuid);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	    @GetMapping
	  public ResponseEntity<PageMovimentacaoDTO> listMovimentacao(
			  @RequestParam(defaultValue = "0") Integer page,
			    @RequestParam(defaultValue = "10") Integer size,
			    @RequestParam(required = false) List<String> sort) {
	    return new ResponseEntity<>(covimentacaoService.list(page, size, sort), HttpStatus.OK);
	  }

	

	
	}
