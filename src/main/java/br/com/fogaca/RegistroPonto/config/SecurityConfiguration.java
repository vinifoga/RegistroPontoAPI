package br.com.fogaca.RegistroPonto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fogaca.RegistroPonto.service.UsuarioService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Autorizacao
	String responsavelRh = "RESPONSAVEL_RH";
	String admin = "ADMIN";
	String colaborador = "COLABORADOR";
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/usuarios").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.POST, "/usuarios").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/usuarios/id/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.GET, "/usuarios/email/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/colaboradores").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.POST, "/colaboradores").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.GET, "/colaboradores/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.PUT, "/colaboradores/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.DELETE, "/colaboradores/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/unidades").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.POST, "/unidades").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/unidades/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.PUT, "/unidades/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.DELETE, "/unidades/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/cargos").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/cargos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.POST, "/cargos").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.PUT, "/cargos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.DELETE, "/cargos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/departamentos").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/departamentos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.POST, "/departamentos").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.PUT, "/departamentos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.DELETE, "/departamentos/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/registros").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.GET, "/registros/**").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.POST, "/registros").hasAnyRole(responsavelRh,colaborador,admin)
		.antMatchers(HttpMethod.PUT, "/registros/**").hasAnyRole(responsavelRh,admin)
		.antMatchers(HttpMethod.DELETE, "/registros/**").hasAnyRole(responsavelRh,admin)
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioService), UsernamePasswordAuthenticationFilter.class);
	}
	
	//Recursos Estaticos
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}