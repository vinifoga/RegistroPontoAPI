package br.com.fogaca.RegistroPonto.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fogaca.RegistroPonto.model.StatusCorrecaoRegistro;

@Controller
public class QrCodeController {

	@GetMapping("/qrcode")
	public ModelAndView novo() {
		DateTimeFormatter hr = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ModelAndView modelAndView = new ModelAndView();
		ZonedDateTime zoned = ZonedDateTime.now();		
		modelAndView.addObject("data", zoned.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).format(dt));		
		modelAndView.addObject("hora", zoned.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).format(hr));
		modelAndView.addObject("status", StatusCorrecaoRegistro.NORMAL.toString());
		modelAndView.setViewName("qrcode");
		return modelAndView;
	}
}
