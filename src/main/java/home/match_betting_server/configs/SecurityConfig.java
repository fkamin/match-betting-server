package home.match_betting_server.configs;

import home.match_betting_server.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.jwt-key}")
    private String securityJwtKey;

    private static final List<String> AUTH_WHITELIST = List.of(
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/actuator/**",
            "/actuator",
            "/swagger-ui/**",
            "/swagger-ui",
            "/swagger-ui/*",
            "/v3/api-docs"
    );

    private static final List<Endpoint> PUBLIC_ENDPOINTS = List.of(
            new Endpoint(HttpMethod.GET, "/api/v1/users/testEndpoint"),
            new Endpoint(HttpMethod.POST, "/api/v1/users/register"),
            new Endpoint(HttpMethod.POST, "/api/v1/users/login"),
            new Endpoint(HttpMethod.GET, "/api/v1/users/debug")
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(AbstractHttpConfigurer::disable);

//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(authorizeRequest -> {
                    AUTH_WHITELIST.forEach(path ->
                            authorizeRequest.requestMatchers(path).permitAll()
                    );
                    PUBLIC_ENDPOINTS.forEach(endpoint ->
                            authorizeRequest.requestMatchers(endpoint.httpMethod, endpoint.pattern).permitAll()
                    );
                    authorizeRequest.anyRequest().authenticated();
                }
        );

//        http.oauth2ResourceServer(oauth2 -> {
//            oauth2.jwt(jwt ->
//                    jwt.decoder())
//        });

        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKey originalKey = SecretKeySpec(jwt)
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3200"));
//        corsConfiguration.setAllowedMethods(List.of("*"));
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        corsConfiguration.setExposedHeaders(List.of("*"));
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private record Endpoint(HttpMethod httpMethod, String pattern) {
    }
}
