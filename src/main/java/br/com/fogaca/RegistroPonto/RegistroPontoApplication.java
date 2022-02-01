package br.com.fogaca.RegistroPonto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class RegistroPontoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroPontoApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("123456789"));
	}

}
