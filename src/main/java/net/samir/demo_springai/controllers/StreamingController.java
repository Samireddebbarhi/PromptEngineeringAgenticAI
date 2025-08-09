package net.samir.demo_springai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamingController {

     private ChatClient chatClient;

     public StreamingController(ChatClient.Builder builder ) {
         this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();

     }

     @GetMapping("/stream")
    public Flux<String> stream(String query) {
         return chatClient.prompt()
                 .user(query)
                 .stream()
                 .content();

     }
     @GetMapping("/nostream")
    public String nostream(String query) {
         return chatClient.prompt()
                 .user(query)
                 .call()
                 .content();
     }
}
