package net.samir.demo_springai.controllers;


import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
public class AIAgentController {
    private ChatClient chatClient;
    private static final Logger logger = LoggerFactory.getLogger(AIAgentController.class);

    public AIAgentController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();

    }

    @GetMapping("/chat")
    public ResponseEntity<String> askLLM(@RequestParam("query") String query) {
        try {
            String response = chatClient.prompt()
                    .user(query)
                    .call()
                    .content();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error processing chat request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}


