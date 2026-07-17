package com.ScheduleMaker.ScheduleMaker.Services;
import reactor.core.publisher.Mono;

public interface GeminiService {
    public Mono<String> generate(String prompt);
}
