package com.ScheduleMaker.ScheduleMaker.ServicesImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ScheduleMaker.ScheduleMaker.Services.GeminiService;

import reactor.core.publisher.Mono;

@Service
public class GeminiServiceImpl implements GeminiService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${GEMINI_API_KEY}")
    private String apiKey;

    
    public GeminiServiceImpl(WebClient geminiClient) {
        this.webClient = geminiClient;
    }

   public Mono<String> generate(String prompt) {

    Map<String, Object> requestBody = buildGeminiRequest(prompt);

    return webClient.post()
            .uri("/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
            .bodyValue(requestBody)
            .retrieve()
            .onStatus(
                status -> !status.is2xxSuccessful(),
                clientResponse ->
                    clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            System.err.println("Gemini API Error Response: " + body);

                            return Mono.<Throwable>error(
                                new RuntimeException(
                                    "API Error: " +
                                    clientResponse.statusCode() +
                                    " - " + body
                                )
                            );
                        })
            )
            .bodyToMono(String.class)
            .map(this::extractResponse)
            .onErrorResume(error -> {
                System.err.println("Error calling Gemini API: " + error.getMessage());
                error.printStackTrace();

                return Mono.just(
                    "Error generating topics: " + error.getMessage()
                );
            });
}

    private Map<String, Object> buildGeminiRequest(String prompt) {
        Map<String, Object> request = new HashMap<>();
        
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> content = new HashMap<>();
        
        List<Map<String, Object>> parts = new ArrayList<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);
        parts.add(part);
        
        content.put("parts", parts);
        contents.add(content);
        
        request.put("contents", contents);
        
        return request;
    }

    private String extractResponse(String responseBody) {
        try {
            if (responseBody == null || responseBody.isBlank()) {
                return "No response from Gemini API";
            }

            JsonNode json = objectMapper.readTree(responseBody);
            JsonNode candidates = json.get("candidates");
            if (candidates != null && candidates.isArray() && candidates.size() > 0) {
                JsonNode firstCandidate = candidates.get(0);
                JsonNode content = firstCandidate.get("content");
                if (content != null) {
                    JsonNode parts = content.get("parts");
                    if (parts != null && parts.isArray() && parts.size() > 0) {
                        JsonNode text = parts.get(0).get("text");
                        if (text != null) {
                            return text.asText();
                        }
                    }
                }
            }
            return "No response from Gemini API";
        } catch (Exception e) {
            System.err.println("Error parsing Gemini response: " + e.getMessage());
            e.printStackTrace();
            return "Error parsing response: " + e.getMessage();
        }
    }
}