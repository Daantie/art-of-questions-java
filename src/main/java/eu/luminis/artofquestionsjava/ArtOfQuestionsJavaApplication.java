package eu.luminis.artofquestionsjava;

import java.io.FileNotFoundException;
import java.util.List;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import eu.luminis.artofquestionsjava.service.DocumentParseService;
import eu.luminis.artofquestionsjava.service.IngestService;
import eu.luminis.artofquestionsjava.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtOfQuestionsJavaApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtOfQuestionsJavaApplication.class);

    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private SearchService searchService;

    public static void main(String[] args) {
        SpringApplication.run(ArtOfQuestionsJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        String pdfContent = documentParseService.parseDocument();
        ingestService.ingestText(pdfContent);
        List<EmbeddingMatch<TextSegment>> results = searchService.search();
        results.forEach(result -> LOGGER.info("- ({}) {}", result.score(), result.embedded().text()));
    }
}
