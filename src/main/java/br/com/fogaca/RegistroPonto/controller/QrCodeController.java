package br.com.fogaca.RegistroPonto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QrCodeController {

	@GetMapping("/qrcode")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/qrcode");
		return modelAndView;
	}
}
