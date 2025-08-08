package net.samir.demo_springai.controllers;

import net.samir.demo_springai.Outputs.ListBook;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructure {
    private ChatClient chatClient;

    public AIAgentStructure(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();

    }

 @GetMapping("/askAgent")
    public ListBook askLLM(String query) {
        String SystemMessage= " you're specialist in FinTech sector ";

        return chatClient.prompt()
                .system(SystemMessage)
                .user(query)
                .call()
                .entity(ListBook.class);
 }

}
