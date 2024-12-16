package fr.insa.mas.VolunteerService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VolunteerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
