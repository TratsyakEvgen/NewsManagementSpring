package by.htp.ex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@EnableWebSecurity
public class SecurityWebConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().authorizeHttpRequests()
				.requestMatchers(new RegexRequestMatcher(".*/admin/.*", null)).hasRole("admin")
				.requestMatchers(new RegexRequestMatcher(".*/auth/.*", null)).authenticated()
				.anyRequest().permitAll().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/news/newsCarousel").failureUrl("/loginError").and().logout()
				.logoutSuccessUrl("/news/newsCarousel");
		return http.build();
	}
	
	
	@Bean
	public SecurityContextLogoutHandler logoutHandler() {
		return new SecurityContextLogoutHandler();
	}

}
