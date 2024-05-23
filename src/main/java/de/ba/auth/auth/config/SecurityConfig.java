package de.ba.auth.auth.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
	}

	// User Creation
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails user1 = User.withUsername("user1")
				.password(encoder.encode("pass"))
				.roles("USER")
				.build();
		UserDetails user2 = User.withUsername("user2")
				.password(encoder.encode("pass"))
				.roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin")
				.password(encoder.encode("pass"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user1, user2, admin);
	}

	// Configuring AuthenticationManager
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}

	// Configuring HttpSecurity
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/**").authenticated()
						.requestMatchers("/app/logout").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.permitAll()
				)
				.exceptionHandling(exceptionHandling -> exceptionHandling
						.accessDeniedPage("/denied")
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/app/logout")
						.invalidateHttpSession(true)
						.permitAll()
						.deleteCookies()
				)
				.httpBasic(Customizer.withDefaults()).build();
	}

	// Password Encoding
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
