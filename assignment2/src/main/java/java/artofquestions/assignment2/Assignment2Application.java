package java.artofquestions.assignment2;

import java.artofquestions.assignment2.service.DocumentParseService;
import java.artofquestions.assignment2.service.IngestService;
import java.artofquestions.assignment2.service.SearchService;
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
public class Assignment2Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment2Application.class);
    public static final String OPENAI_RESPONSE = "openaiResponse";
    public static final String HUGGINGFACE_RESPONSE = "huggingFaceResponse";

    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private SearchService searchService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment2Application.class, args);
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        String pdfContent = documentParseService.parseDocument();
        ingestService.ingestText(pdfContent);
        List<EmbeddingMatch<TextSegment>> results = searchService.search();
        results.forEach(result -> LOGGER.info("- ({}) {}", result.score(), result.embedded().text()));
    }
}
