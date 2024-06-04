package prosolutions.myschool;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class MyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyschoolApplication.class, args);

	}
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
//				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
//		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
//				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//		return new CorsFilter();
//	}

}
