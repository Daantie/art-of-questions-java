package artofquestions.assignment1;

import artofquestions.assignment1.service.ChatService;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment1Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment1Application.class);
    public static final String OPENAI_RESPONSE = "openaiResponse";
    public static final String HUGGINGFACE_RESPONSE = "huggingFaceResponse";

    @Autowired
    private ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment1Application.class, args).close();
    }

    @Override
    public void run(String... args) {
        Map<String, String> chatResponses = chatService.chat();

        LOGGER.info("OpenAI response:");
        if (chatResponses.get(OPENAI_RESPONSE) == null || chatResponses.get(OPENAI_RESPONSE).isEmpty()) {
            LOGGER.info(":( No response :(");
        } else {
            LOGGER.info(chatResponses.get(OPENAI_RESPONSE));
        }

        LOGGER.info("HuggingFace response:");
        if (chatResponses.get(HUGGINGFACE_RESPONSE) == null || chatResponses.get(HUGGINGFACE_RESPONSE).isEmpty()) {
            LOGGER.info(":( No response :(");
        } else {
            LOGGER.info(chatResponses.get(HUGGINGFACE_RESPONSE));
        }
    }
}
