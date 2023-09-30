package artofquestions.assignment4;

import artofquestions.assignment4.service.DocumentParseService;
import artofquestions.assignment4.service.FunctionCallService;
import artofquestions.assignment4.service.IngestService;
import java.io.IOException;
import java.util.List;

import dev.langchain4j.data.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment4Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Assignment4Application.class);
    @Autowired
    private DocumentParseService documentParseService;
    @Autowired
    private IngestService ingestService;
    @Autowired
    private FunctionCallService functionCallService;

    public static void main(String[] args) {
        SpringApplication.run(Assignment4Application.class, args).close();
    }


    @Override
    public void run(String... args) throws IOException {
//        LOGGER.info("Parsing FAQ data...");
//        Document faqData = documentParseService.parseFaqData();
//        LOGGER.info("Ingesting FAQ data...");
//        ingestService.ingestDocument(faqData);
//
//        LOGGER.info("Parsing Talks data...");
//        List<Document> talkData = documentParseService.parseTalksData();
//        LOGGER.info("Ingesting Talks data...");
//        ingestService.ingestDocuments(talkData);

        LOGGER.info("Are there any talks about machine learning?");
        LOGGER.info(functionCallService.callFunction("Are there any talks about machine learning?"));
        LOGGER.info("Who do I contact to be a sponsor?");
        LOGGER.info(functionCallService.callFunction("Who do I contact to be a sponsor?"));
    }
}
