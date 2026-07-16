package com.ScheduleMaker.ScheduleMaker.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to load environment variables from .env file
 * This runs when the application starts
 */
@Configuration
public class EnvConfig {
    
    static {
        Dotenv dotenv = Dotenv.configure()
                .directory(".")  // Load from current working directory (project root)
                .ignoreIfMissing()
                .load();
        
        // Set system properties from .env file so Spring can access them
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
