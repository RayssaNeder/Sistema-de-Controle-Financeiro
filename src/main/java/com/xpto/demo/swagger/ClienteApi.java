package com.xpto.demo.swagger;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xpto.demo.dto.Cliente;
import com.xpto.demo.dto.PageCliente;
import com.xpto.demo.dto.UpdateCliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
@RequestMapping("/cliente")
public interface ClienteApi {

	@Operation(summary = "Obter cliente por UUID", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	@GetMapping("/{uuid}")
	ResponseEntity<Cliente> readCliente(@PathVariable UUID uuid);

	@Operation(summary = "Atualizar cliente existente", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso", content = @Content(schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	@PutMapping("/{uuid}")
	ResponseEntity<Cliente> updateCliente(@PathVariable UUID uuid, @RequestBody UpdateCliente updateCliente);

	@Operation(summary = "Deletar cliente por UUID", responses = {
			@ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	@DeleteMapping("/{uuid}")
	ResponseEntity<Void> deleteCliente(@PathVariable UUID uuid);

	@Operation(summary = "Listar clientes com paginação", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso", content = @Content(schema = @Schema(implementation = PageCliente.class))) })
	@GetMapping
	ResponseEntity<PageCliente> listCliente(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort);
}
