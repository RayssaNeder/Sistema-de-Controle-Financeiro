package com.xpto.demo.swagger;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xpto.demo.dto.ContaDTO;
import com.xpto.demo.dto.CreateContaDTO;
import com.xpto.demo.dto.PageConta;
import com.xpto.demo.dto.UpdateConta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Conta", description = "Operações relacionadas a contas")
@RequestMapping("/conta")
public interface ContaApi {

	@Operation(summary = "Criar nova conta", responses = {
			@ApiResponse(responseCode = "201", description = "Conta criada com sucesso", content = @Content(schema = @Schema(implementation = ContaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida") })
	@PostMapping("/nova")
	ResponseEntity<ContaDTO> createConta(@RequestBody CreateContaDTO createConta);

	@Operation(summary = "Obter conta por UUID", responses = {
			@ApiResponse(responseCode = "200", description = "Conta encontrada", content = @Content(schema = @Schema(implementation = ContaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Conta não encontrada") })
	@GetMapping("/{uuid}")
	ResponseEntity<ContaDTO> readConta(@PathVariable UUID uuid);

	@Operation(summary = "Atualizar conta existente", responses = {
			@ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso", content = @Content(schema = @Schema(implementation = ContaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Conta não encontrada") })
	@PutMapping("/{uuid}")
	ResponseEntity<ContaDTO> updateConta(@PathVariable UUID uuid, @RequestBody UpdateConta updateConta);

	@Operation(summary = "Deletar conta por UUID", responses = {
			@ApiResponse(responseCode = "204", description = "Conta deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Conta não encontrada") })
	@DeleteMapping("/{uuid}")
	ResponseEntity<Void> deleteConta(@PathVariable UUID uuid);

	@Operation(summary = "Listar contas com paginação", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de contas retornada com sucesso", content = @Content(schema = @Schema(implementation = PageConta.class))) })
	@GetMapping
	ResponseEntity<PageConta> listConta(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort);
}
