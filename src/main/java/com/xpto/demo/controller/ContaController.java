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

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;
import com.xpto.demo.service.ContaService;
import com.xpto.demo.swagger.ContaApi;

@RestController
@RequestMapping("/conta")
public class ContaController implements ContaApi {
	private final ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@PostMapping("/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ContaDTO> createConta(@RequestBody CreateContaDTO createconta) {
		return new ResponseEntity<>(contaService.create(createconta), HttpStatus.CREATED);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<ContaDTO> readConta(@PathVariable UUID uuid) {
		return new ResponseEntity<>(contaService.read(uuid), HttpStatus.OK);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<ContaDTO> updateConta(@PathVariable UUID uuid, @RequestBody UpdateConta updateconta) {
		return new ResponseEntity<>(contaService.update(uuid, updateconta), HttpStatus.OK);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> deleteConta(@PathVariable UUID uuid) {
		contaService.delete(uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<PageConta> listConta(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort) {
		return new ResponseEntity<>(contaService.list(page, size, sort), HttpStatus.OK);
	}
}
