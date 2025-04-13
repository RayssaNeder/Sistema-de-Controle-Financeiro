package com.xpto.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.demo.dto.CreateEndereco;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.PageEnderecoDTO;
import com.xpto.demo.service.EnderecoService;
import com.xpto.demo.swagger.EnderecoApi;

@RestController
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoApi {
	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService EnderecoService) {
		this.enderecoService = EnderecoService;
	}

	@PostMapping("/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody CreateEndereco createEndereco) {
		return new ResponseEntity<>(enderecoService.create(createEndereco), HttpStatus.CREATED);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<EnderecoDTO> readEndereco(@PathVariable UUID uuid) {
		return new ResponseEntity<>(enderecoService.read(uuid), HttpStatus.OK);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> deleteEndereco(@PathVariable UUID uuid) {
		enderecoService.delete(uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-all-by-cliente/{uuidCliente}")
	public ResponseEntity<List<EnderecoDTO>> readAllENderecoByCliente(@PathVariable UUID uuidCliente) {
		return new ResponseEntity<>(enderecoService.getAllEnderecosByCliente(uuidCliente), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<PageEnderecoDTO> listEndereco(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort) {
		return new ResponseEntity<>(enderecoService.list(page, size, sort), HttpStatus.OK);
	}
}
