package java.artofquestions.assignment3;

import java.artofquestions.assignment3.service.DocumentParseService;
import java.artofquestions.assignment3.service.IngestService;
import java.artofquestions.assignment3.service.QuestionAnswerService;
import java.artofquestions.assignment3.service.SearchService;
import java.io.FileNotFoundException;
import java.util.List;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment3Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment3Application.class);
    public static final String OPENAI_RESPONSE = "openaiResponse";
    public static final String HUGGINGFACE_RESPONSE = "huggingFaceResponse";

    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private QuestionAnswerService questionAnswerService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment3Application.class, args);
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        String question = "Who should I contact to become a sponsor?";
        String pdfContent = documentParseService.parseDocument();
        ingestService.ingestText(pdfContent);
        List<EmbeddingMatch<TextSegment>> results = searchService.search(question);
        List<String> resultsAsStrings = results.stream().map(embeddingMatch -> embeddingMatch.embedded().text()).toList();
        String openaiAnswer = questionAnswerService.generateOpenAiAnswer(question, resultsAsStrings);
        String huggingFaceAnswer = questionAnswerService.generateHuggingFaceAnswer(question, resultsAsStrings);

        LOGGER.info("OpenAI answer:\n{}\n\n", openaiAnswer);
        if (huggingFaceAnswer != null) {
            LOGGER.info("HuggingFace answer:\n{}", huggingFaceAnswer);
        }
    }
}
