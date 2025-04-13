package com.xpto.demo.swagger;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xpto.demo.dto.CreateEndereco;
import com.xpto.demo.dto.EnderecoDTO;
import com.xpto.demo.dto.PageEnderecoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endereço", description = "Operações relacionadas a endereços")
@RequestMapping("/endereco")
public interface EnderecoApi {

	@Operation(summary = "Criar novo endereço", responses = {
			@ApiResponse(responseCode = "201", description = "Endereço criado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida") })
	@PostMapping("/novo")
	ResponseEntity<EnderecoDTO> createEndereco(@RequestBody CreateEndereco createEndereco);

	@Operation(summary = "Obter endereço por UUID do cliente e do endereço", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço encontrado", content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado") })
	@GetMapping("/{uuid}")
	ResponseEntity<EnderecoDTO> readEndereco(@PathVariable UUID uuid);

	@Operation(summary = "Deletar endereço por UUID", responses = {
			@ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado") })
	@DeleteMapping("/{uuid}")
	ResponseEntity<Void> deleteEndereco(@PathVariable UUID uuid);

	@Operation(summary = "Listar endereços com paginação", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso", content = @Content(schema = @Schema(implementation = PageEnderecoDTO.class))) })
	@GetMapping
	ResponseEntity<PageEnderecoDTO> listEndereco(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false) List<String> sort);
}
