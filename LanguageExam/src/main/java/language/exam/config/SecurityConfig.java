package language.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import language.exam.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	MvcRequestMatcher.Builder  mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(mvc.pattern("/register-account")).permitAll()
				.requestMatchers(mvc.pattern("/exams-guide")).permitAll()
				.requestMatchers(mvc.pattern("/exam-details/{*}")).permitAll()
				.requestMatchers(mvc.pattern("/info-details/{*}")).permitAll()
				.requestMatchers(mvc.pattern("/admin-exams-for-admin")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/add-exam-for-admin")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/change-exam-date-for-admin/{*}")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/cancel-exam-for-admin/{*}")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/admin-infos-for-admin")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/add-info-for-admin")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/edit-info-for-admin/{*}")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/delete-info-for-admin/{*}")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/admin-fees-for-admin")).hasAuthority("ROLE_ADMIN")
				.requestMatchers(mvc.pattern("/edit-fees-for-admin")).hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
		);
		
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login?error")
				.usernameParameter("mail")
				.passwordParameter("pass")
				.defaultSuccessUrl("/exams-guide", true)
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
		).logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
		).exceptionHandling(exception -> exception
				.accessDeniedPage("/login")
		);
	
		return http.build();
	}
	
	
}