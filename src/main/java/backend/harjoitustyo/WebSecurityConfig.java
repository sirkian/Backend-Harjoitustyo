package backend.harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import backend.harjoitustyo.service.CsrfLoggerFilter;
import backend.harjoitustyo.service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests(auth -> {
			auth.antMatchers("/", "/login", "/signup", "/saveuser", "/images/**", "/image", "/download/**", "/categories").permitAll();
			auth.antMatchers("/css/**", "/icons/**", "/h2-console", "/h2-console/**", "/rest", "/rest/**").permitAll();
			auth.antMatchers("/upload", "/upload/upload").hasAnyAuthority("USER", "ADMIN");
			auth.antMatchers("/delete", "/delete/**").hasAuthority("ADMIN");
			auth.anyRequest().authenticated();
		})
				.headers().frameOptions().disable()
				.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/", true)
				.and()
				.logout().permitAll().logoutSuccessUrl("/")
				.and()
				.httpBasic();
		
		 http.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class);
		 return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
