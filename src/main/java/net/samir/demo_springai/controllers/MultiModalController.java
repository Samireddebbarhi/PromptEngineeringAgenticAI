package net.samir.demo_springai.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModalController {
    private ChatClient chatClient;

   @Value("classpath:/images/Certificate.jpeg")
    private Resource image;
    public MultiModalController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/describe")
    public String describeImage() {
        return chatClient.prompt()
                .system("Describe to me the attached image")
                .user(u -> u.text("Describe to me this image")
                        .media(MediaType.IMAGE_JPEG, image))
                .call().content();
    }
    @GetMapping("/askImage")
        public String askImage(String question)
        {
            return chatClient.prompt()
                    .system("reply to question given by user")
                    .user(u -> u.text(question)
                            .media(MediaType.IMAGE_JPEG,image))
                    .call().content();
        }
    }

