package java.artofquestions.assignment3;

import java.artofquestions.assignment3.service.DocumentParseService;
import java.artofquestions.assignment3.service.IngestService;
import java.artofquestions.assignment3.service.QuestionAnswerService;
import java.io.FileNotFoundException;

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
        String pdfContent = documentParseService.parseDocument();
        ingestService.ingestText(pdfContent);
        String openaiAnswer = questionAnswerService.generateOpenAiAnswer();
        String huggingFaceAnswer = questionAnswerService.generateHuggingFaceAnswer();

        if (openaiAnswer != null) {
            LOGGER.info("OpenAI answer:\n{}\n\n", openaiAnswer);
        } else {
            LOGGER.info("No OpenAI answer.\n\n");
        }
        if (huggingFaceAnswer != null) {
            LOGGER.info("HuggingFace answer:\n{}", huggingFaceAnswer);
        } else {
            LOGGER.info("No HuggingFace answer.");
        }
    }
}
