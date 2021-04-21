package com.cognizant.springlearn.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("ramesh")
		.password(passwordEncoder().encode("password"))
		.roles("USER")
		.and()
		.withUser("admin").password(passwordEncoder().encode("admin"))
		.roles("ADMIN");

	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity.csrf().disable().httpBasic().and()
	 * .authorizeRequests().antMatchers("/countries").hasRole("USER");     }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	http.csrf().disable().httpBasic().and().authorizeRequests()
			//.antMatchers("/countries").hasRole("USER") 
		http.
		csrf().disable().httpBasic().and()
				.authorizeRequests()
				.antMatchers("/user").hasRole("USER")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/countries").hasAnyRole("USER", "ADMIN")
				.antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtAuthorizationFilter(authenticationManager()))
				.formLogin();

	}
	
	
//	 @Override 
//	protected void configure(HttpSecurity httpSecurity) throws Exception { 
//		 httpSecurity.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/user").hasAnyRole("USER","ADMIN")
//			.antMatchers("/admin").hasRole("ADMIN")
//			.antMatchers("/authenticate").hasRole("ADMIN")
//			.antMatchers("/countries").hasAnyRole("USER", "ADMIN")
//			.and()
//			.formLogin();
//		}


}
