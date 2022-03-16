package br.com.fogaca.RegistroPonto.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fogaca.RegistroPonto.model.StatusCorrecaoRegistro;

@Controller
public class QrCodeController {

	@GetMapping("/qrcode")
	public ModelAndView novo() {
		DateTimeFormatter hr = DateTimeFormatter.ofPattern("hh:mm:ss");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("data", LocalDate.now());
		modelAndView.addObject("hora", LocalDateTime.now().format(hr));
		modelAndView.addObject("status", StatusCorrecaoRegistro.NORMAL.toString());
		modelAndView.setViewName("/qrcode");
		return modelAndView;
	}
}
