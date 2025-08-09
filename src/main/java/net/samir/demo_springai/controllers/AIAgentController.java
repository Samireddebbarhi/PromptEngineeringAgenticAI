package net.samir.demo_springai.controllers;


import org.jboss.logging.Messages;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import java.util.List;

@RestController
public class AIAgentController {

    private ChatClient chatClient;
    private static final Logger logger = LoggerFactory.getLogger(AIAgentController.class);

    public AIAgentController( ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build()).build();

    }

    @GetMapping("/chat")
    public ResponseEntity<String> askLLM(@RequestParam("query") String query) {
        try {
            List<Message> examples = List.of(new UserMessage("2+7"),new AssistantMessage("9"));

            String response = chatClient.prompt()
                    .user(query)
                    .system("Always respond with UPPERCASE0")
                    .messages(examples)
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


