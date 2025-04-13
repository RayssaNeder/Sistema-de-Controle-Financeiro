package com.xpto.demo.swagger;

import com.xpto.demo.dto.RelatorioReceitaDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoGeralDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Tag(name = "Relatório", description = "Operações relacionadas a geração e download de relatórios")
@RequestMapping("/relatorio")
public interface RelatorioApi {

    @Operation(summary = "Gerar relatório de saldo de cliente",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso",
                                content = @Content(schema = @Schema(implementation = RelatorioSaldoClienteDTO.class))),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-cliente")
    ResponseEntity<RelatorioSaldoClienteDTO> gerarRelatorioSaldoCliente(@RequestParam UUID uuidCliente);

    @Operation(summary = "Download do relatório de saldo de cliente em formato TXT",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório de saldo de cliente gerado com sucesso"),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-cliente/txt")
    ResponseEntity<byte[]> downloadRelatorioSaldoClienteTxt(@RequestParam UUID uuidCliente);

    @Operation(summary = "Gerar relatório de saldo de cliente por período",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso",
                                content = @Content(schema = @Schema(implementation = RelatorioSaldoClienteDTO.class))),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-cliente-por-periodo")
    ResponseEntity<RelatorioSaldoClienteDTO> gerarRelatorioSaldoClienteByPeriodo(
            @RequestParam UUID uuidCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

    @Operation(summary = "Download do relatório de saldo de cliente por período em formato TXT",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório de saldo de cliente por período gerado com sucesso"),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-cliente-por-periodo/txt")
    ResponseEntity<byte[]> downloadRelatorioSaldoClienteByPeriodoTxt(
            @RequestParam UUID uuidCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

    @Operation(summary = "Gerar relatório de saldo geral",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso",
                                content = @Content(schema = @Schema(implementation = RelatorioSaldoGeralDTO.class))),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-geral")
    ResponseEntity<List<RelatorioSaldoGeralDTO>> gerarRelatorioSaldodeGeral();

    @Operation(summary = "Download do relatório de saldo geral em formato TXT",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório de saldo geral gerado com sucesso"),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/saldo-geral/txt")
    ResponseEntity<byte[]> downloadRelatorioSaldoGeralTxt();

    @Operation(summary = "Gerar relatório de receitas por período",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso",
                                content = @Content(schema = @Schema(implementation = RelatorioReceitaDTO.class))),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/receitas")
    ResponseEntity<List<RelatorioReceitaDTO>> gerarRelatoriodeReceitas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

    @Operation(summary = "Download do relatório de receitas em formato TXT",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Relatório de receitas gerado com sucesso"),
                   @ApiResponse(responseCode = "400", description = "Requisição inválida")
               })
    @GetMapping("/receitas/txt")
    ResponseEntity<byte[]> downloadRelatorioReceitaTxt(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);
}

