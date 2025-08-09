package net.samir.demo_springai.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "/askImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String askImage(
            @RequestParam("question") String question,
            @RequestParam("file") MultipartFile file
    ) {
        return chatClient.prompt()
                .system("reply to question given by users")
                .user(u -> u.text(question)
                        .media(MediaType.IMAGE_JPEG, file.getResource()))
                .call().content();
    }
}
