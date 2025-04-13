package com.xpto.demo.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.demo.dto.RelatorioReceitaDTO;
import com.xpto.demo.dto.RelatorioSaldoClienteDTO;
import com.xpto.demo.dto.RelatorioSaldoGeralDTO;
import com.xpto.demo.service.RelatorioService;
import com.xpto.demo.service.utils.RelatorioUtils;
import com.xpto.demo.swagger.RelatorioApi;


@RestController
@RequestMapping("/relatorio")
public class RelatorioController implements RelatorioApi {
	private final RelatorioService relatorioService;

	  public RelatorioController(RelatorioService relatorioService) {
	    this.relatorioService = relatorioService;
	  }

	  @GetMapping("/saldo-cliente")
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<RelatorioSaldoClienteDTO> gerarRelatorioSaldoCliente(@RequestParam UUID uuidCliente) {
	    return new ResponseEntity<>(relatorioService.gerarRelatorioSaldoCliente(uuidCliente), HttpStatus.OK);
	  }
	  
		@GetMapping("/saldo-cliente/txt")
	  public ResponseEntity<byte[]> downloadRelatorioSaldoClienteTxt(
			  @RequestParam UUID uuidCliente) {

	      RelatorioSaldoClienteDTO relatorio = relatorioService.gerarRelatorioSaldoCliente(uuidCliente);
	      String textoRelatorio = relatorio.gerarTextoRelatorio();

	      byte[] conteudo = textoRelatorio.getBytes(StandardCharsets.UTF_8);

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.TEXT_PLAIN);
	      headers.setContentDisposition(ContentDisposition
	              .attachment()
	              .filename("relatorio_saldo_cliente_" + uuidCliente + ".txt")
	              .build());

	      return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
	  }
	  
	  @GetMapping("/saldo-cliente-por-periodo")
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<RelatorioSaldoClienteDTO> gerarRelatorioSaldoClienteByPeriodo(
			  @RequestParam UUID uuidCliente,
		        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
		        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
	    return new ResponseEntity<>(relatorioService.gerarRelatorioSaldoClienteByPeriodo(uuidCliente, dataInicio, dataFim), HttpStatus.OK);
	  }
	  
	  @GetMapping("/saldo-cliente-por-periodo/txt")
	  public ResponseEntity<byte[]> downloadRelatorioSaldoClienteByPeriodoTxt(
			  @RequestParam UUID uuidCliente,
	          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
	          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

	      RelatorioSaldoClienteDTO relatorio = relatorioService.gerarRelatorioSaldoClienteByPeriodo(uuidCliente, dataInicio, dataFim);
	      String textoRelatorio = relatorio.gerarTextoRelatorio();

	      byte[] conteudo = textoRelatorio.getBytes(StandardCharsets.UTF_8);

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.TEXT_PLAIN);
	      headers.setContentDisposition(ContentDisposition
	              .attachment()
	              .filename("relatorio_saldo_cliente_by_periodo_" + uuidCliente + ".txt")
	              .build());

	      return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
	  }
 

	  @GetMapping("/saldo-geral")
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<List<RelatorioSaldoGeralDTO>> gerarRelatorioSaldodeGeral() {
	    return new ResponseEntity<>(relatorioService.gerarRelatorioSaldoGeral(), HttpStatus.OK);
	  }
	  
	  @GetMapping("/saldo-geral/txt")
	  public ResponseEntity<byte[]> downloadRelatorioSaldoGeralTxt() {

	      List<RelatorioSaldoGeralDTO> relatorio = relatorioService.gerarRelatorioSaldoGeral();
	      String textoRelatorio = RelatorioUtils.gerarTextoRelatorio(relatorio);

	      byte[] conteudo = textoRelatorio.getBytes(StandardCharsets.UTF_8);

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.TEXT_PLAIN);
	      headers.setContentDisposition(ContentDisposition
	              .attachment()
	              .filename("relatorio_saldo_geral.txt")
	              .build());

	      return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
	  }

	  
	  @GetMapping("/receitas")
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<List<RelatorioReceitaDTO>> gerarRelatoriodeReceitas(
			  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
		        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
	    return new ResponseEntity<>(relatorioService.gerarRelatorioReceita(dataInicio, dataFim), HttpStatus.OK);
	  }
	  
	  
	  @GetMapping("/receitas/txt")
	  public ResponseEntity<byte[]> downloadRelatorioReceitaTxt(
			  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
		        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

	      List<RelatorioReceitaDTO> relatorio = relatorioService.gerarRelatorioReceita(dataInicio, dataFim);
	      String textoRelatorio = RelatorioUtils.gerarTextoRelatorio(relatorio);

	      byte[] conteudo = textoRelatorio.getBytes(StandardCharsets.UTF_8);

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.TEXT_PLAIN);
	      headers.setContentDisposition(ContentDisposition
	              .attachment()
	              .filename("relatorio_saldo_geral.txt")
	              .build());

	      return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
	  }
	
	}
