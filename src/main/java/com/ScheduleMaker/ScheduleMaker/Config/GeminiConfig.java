package com.ScheduleMaker.ScheduleMaker.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeminiConfig {

    @Value("${GEMINI_API_KEY}")
    private String apiKey;

    @Bean
    public WebClient geminiClient() {
        return WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

    @Bean
    public String geminiApiKey() {
        return apiKey;
    }
}
