package artofquestions.assignment2;

import artofquestions.assignment2.service.DocumentParseService;
import artofquestions.assignment2.service.IngestService;
import artofquestions.assignment2.service.SearchService;
import java.io.FileNotFoundException;
import java.util.List;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment2Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment2Application.class);

    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private SearchService searchService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment2Application.class, args).close();
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        LOGGER.info("Parsing FAQ data...");
        Document faqData = documentParseService.parseFaqData();
        LOGGER.info("Ingesting FAQ data...");
        ingestService.ingestDocument(faqData);
        List<TextSegment> results = searchService.search();
        LOGGER.info("Results found:");
        results.forEach(result -> LOGGER.info("- {}", result.text()));
    }
}
