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

import com.xpto.demo.dto.CreateMovimentacaoDTO;
import com.xpto.demo.dto.MovimentacaoDTO;
import com.xpto.demo.dto.MovivemtosByClienteDTO;
import com.xpto.demo.dto.PageMovimentacaoDTO;
import com.xpto.demo.dto.UpdateMovimentacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Movimentação", description = "Operações relacionadas a movimentações")
@RequestMapping("/movimentacao")
public interface MovimentacaoApi {

	@Operation(summary = "Criar nova movimentação", responses = {
			@ApiResponse(responseCode = "201", description = "Movimentação criada com sucesso", content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida") })
	@PostMapping("/nova")
	ResponseEntity<MovimentacaoDTO> createMovimentacao(@RequestBody CreateMovimentacaoDTO createMovimentacao);

	@Operation(summary = "Obter movimentação por UUID", responses = {
			@ApiResponse(responseCode = "200", description = "Movimentação encontrada", content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada") })
	@GetMapping("/{uuid}")
	ResponseEntity<MovimentacaoDTO> readMovimentacao(@PathVariable UUID uuid);

	@Operation(summary = "Listar movimentações por cliente", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de movimentações retornada com sucesso", content = @Content(schema = @Schema(implementation = List.class))) })
	@GetMapping("/get-all-by-cliente/{uuidCliente}")
	ResponseEntity<List<MovivemtosByClienteDTO>> readAllMovimentacoesByCliente(@PathVariable UUID uuidCliente);

	@Operation(summary = "Atualizar movimentação por UUID", responses = {
			@ApiResponse(responseCode = "200", description = "Movimentação atualizada com sucesso", content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada") })
	@PutMapping("/{uuid}")
	ResponseEntity<MovimentacaoDTO> updateMovimentacao(@PathVariable UUID uuid,
			@RequestBody UpdateMovimentacao updateMovimentacao);

	@Operation(summary = "Deletar movimentação por UUID", responses = {
			@ApiResponse(responseCode = "204", description = "Movimentação deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada") })
	@DeleteMapping("/{uuid}")
	ResponseEntity<Void> deleteMovimentacao(@PathVariable UUID uuid);

	@Operation(summary = "Listar movimentações com paginação", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de movimentações retornada com sucesso", content = @Content(schema = @Schema(implementation = PageMovimentacaoDTO.class))) })
	@GetMapping
	ResponseEntity<PageMovimentacaoDTO> listMovimentacao(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort);
}
