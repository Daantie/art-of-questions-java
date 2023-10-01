package artofquestions.assignment3;

import artofquestions.assignment3.service.DocumentParseService;
import artofquestions.assignment3.service.IngestService;
import artofquestions.assignment3.service.QuestionAnswerService;
import java.io.FileNotFoundException;

import dev.langchain4j.data.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment3Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment3Application.class);

    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private QuestionAnswerService questionAnswerService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment3Application.class, args);
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        LOGGER.info("Parsing FAQ data...");
        Document faqData = documentParseService.parseFaqData();
        LOGGER.info("Ingesting FAQ data...");
        ingestService.ingestDocument(faqData);
        String openaiAnswer = questionAnswerService.generateOpenAiAnswer();
        String huggingFaceAnswer = questionAnswerService.generateHuggingFaceAnswer();

        if (openaiAnswer != null) {
            LOGGER.info("");
            LOGGER.info("OpenAI answer:");
            LOGGER.info("{}", openaiAnswer);
        } else {
            LOGGER.info("No OpenAI answer.");
        }
        if (huggingFaceAnswer != null) {
            LOGGER.info("");
            LOGGER.info("HuggingFace answer:");
            LOGGER.info("{}", huggingFaceAnswer);
        } else {
            LOGGER.info("No HuggingFace answer.");
        }
    }
}
