package br.com.fogaca.RegistroPonto.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fogaca.RegistroPonto.controller.dto.ColaboradorDto;
import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.service.CalculaBancoService;
import br.com.fogaca.RegistroPonto.service.RegistroService;

@RestController
@RequestMapping("/calcula-banco")
public class CalculaBancoController {
	
	@Autowired CalculaBancoService calculaService;
	@Autowired RegistroService registroService;
	
	@GetMapping("/{id}")
	public ResponseEntity<LocalTime> calculaBanco(@PathVariable Long id) {
		LocalTime calculaBanco;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate hoje = LocalDate.parse(LocalDate.now().format(formatter));
			List<Registro> listaRegistro = registroService.findByDataAndColaborador_Matricula(hoje, id);
			calculaBanco = calculaService.calculaBanco(id,listaRegistro);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(calculaBanco);
		
	}
	
}
