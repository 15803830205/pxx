package pan.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class corsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);   //cookie跨域
        config.setAllowedOrigins(Arrays.asList("*")); //原始域
        config.setAllowedHeaders(Arrays.asList("*")); //允许头
        config.setAllowedMethods(Arrays.asList("*"));  //允许的方法  get post put delete
        config.setMaxAge(300l);  //缓存时间

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
