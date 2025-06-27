package cl.ucm.BackBook.backbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                .requestMatchers("/api/book/new").hasRole("ADMIN")  // solo ADMIN
                .requestMatchers("/api/book/all").hasAnyRole("ADMIN", "LECTOR") // ambos roles
                .requestMatchers("/api/copia/new").hasRole("ADMIN")
                .requestMatchers("/api/copia/all").hasAnyRole("ADMIN", "LECTOR")
                .requestMatchers("/api/prestamo/new", "/api/prestamo/devolver/**").hasRole("ADMIN")
                .requestMatchers("/api/prestamo/all").hasAnyRole("ADMIN", "LECTOR")
                .requestMatchers("/api/prestamo/usuario/**").hasAnyRole("ADMIN", "LECTOR")
                .requestMatchers("/api/multa/new", "/api/multa/pagar/**").hasRole("ADMIN")
                .requestMatchers("/api/multa/usuario/**").hasAnyRole("ADMIN", "LECTOR")

                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
