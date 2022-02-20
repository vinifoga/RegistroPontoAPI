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
	String responsavelRh = "ROLE_RESPONSAVEL_RH";
	String admin = "ROLE_ADMIN";
	String colaborador = "ROLE_COLABORADOR";
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/usuarios").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/usuarios").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/usuarios/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.PUT, "/usuarios/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/usuarios/**").hasRole(colaborador)
		.antMatchers(HttpMethod.PUT, "/usuarios/**").hasRole(colaborador)
		.antMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/colaboradores").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/colaboradores").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/colaboradores").hasRole(colaborador)
		.antMatchers(HttpMethod.GET, "/colaboradores/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.PUT, "/colaboradores/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/colaboradores/**").hasRole(colaborador)
		.antMatchers(HttpMethod.PUT, "/colaboradores/**").hasRole(colaborador)
		.antMatchers(HttpMethod.DELETE, "/colaboradores/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/unidades").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/unidades").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/unidades/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/unidades/**").hasRole(colaborador)
		.antMatchers(HttpMethod.PUT, "/unidades/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.DELETE, "/unidades/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/cargos").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/cargos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/cargos").hasRole(responsavelRh)
		.antMatchers(HttpMethod.PUT, "/cargos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.DELETE, "/cargos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/departamentos").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/departamentos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/departamentos").hasRole(responsavelRh)
		.antMatchers(HttpMethod.PUT, "/departamentos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.DELETE, "/departamentos/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/registros").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/registros/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.GET, "/registros/**").hasRole(colaborador)
		.antMatchers(HttpMethod.POST, "/registros").hasRole(responsavelRh)
		.antMatchers(HttpMethod.POST, "/registros").hasRole(colaborador)
		.antMatchers(HttpMethod.PUT, "/registros/**").hasRole(responsavelRh)
		.antMatchers(HttpMethod.DELETE, "/registros/**").hasRole(responsavelRh)
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