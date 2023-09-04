package by.htp.ex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityWebConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().authorizeHttpRequests().requestMatchers("/**").permitAll()
				.requestMatchers("/files/**").hasRole("admin").and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/news/newsCarousel").failureUrl("/loginError").and().logout()
				.logoutSuccessUrl("/news/newsCarousel");
		return http.build();
	}

}
