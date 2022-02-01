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
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/usuarios").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/usuarios").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/usuarios/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/usuarios/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/colaboradores").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/colaboradores").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/colaboradores").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.GET, "/colaboradores/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.PUT, "/colaboradores/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/colaboradores/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.PUT, "/colaboradores/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.DELETE, "/colaboradores/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/unidades").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/unidades").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/unidades/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/unidades/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.PUT, "/unidades/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.DELETE, "/unidades/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/cargos").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/cargos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/cargos").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.PUT, "/cargos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.DELETE, "/cargos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/departamentos").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/departamentos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/departamentos").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.PUT, "/departamentos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.DELETE, "/departamentos/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/registros").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/registros/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.GET, "/registros/**").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.POST, "/registros").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.POST, "/registros").hasRole("COLABORADOR")
		.antMatchers(HttpMethod.PUT, "/registros/**").hasRole("RESPONSAVEL_RH")
		.antMatchers(HttpMethod.DELETE, "/registros/**").hasRole("RESPONSAVEL_RH")
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