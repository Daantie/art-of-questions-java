package artofquestions.assignment4.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DocumentParseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentParseService.class);
    private final String UNSTRUCTURED_API_KEY = System.getenv("UNSTRUCTURED_API_KEY");
    private final CsvMapper mapper = new CsvMapper();

    public Document parseFaqData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:data/faq.pdf");
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.unstructured.io/general/v0/")
                .defaultHeader("unstructured-api-key", UNSTRUCTURED_API_KEY)
                .build();

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("files", fileSystemResource);
        builder.part("strategy", "ocr_only");
        builder.part("ocr_languages", "eng");

        Mono<Object> mono = webClient.post()
                .uri("general")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(UnstructuredResponse[].class);
                    } else {
                        LOGGER.error("Something went wrong when uploading file to Unstructured API. Received status code {}", response.statusCode());
                        return response.bodyToMono(JsonNode.class);
                    }
                });

        Object response = mono.block(Duration.ofMinutes(1));
        if (response instanceof JsonNode jsonNode) {
            LOGGER.error("Response: {}", jsonNode);
            return null;
        }
        if (response instanceof UnstructuredResponse[] unstructuredResponses) {
            return Document.from(Arrays.stream(unstructuredResponses).map(UnstructuredResponse::getText).collect(Collectors.joining(" ")));
        }
        return null;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class UnstructuredResponse {
        private String text;
    }

    public List<Document> parseTalksData() throws IOException {
        List<Document> documents = new ArrayList<>();
        CsvSchema schema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumn("Position")
                .addColumn("Title")
                .addColumn("Details")
                .addColumn("talk_id")
                .addColumn("talk_url")
                .addColumn("talk_desc")
                .addColumn("speakers")
                .addColumn("talk_times")
                .build();

        File file = ResourceUtils.getFile("classpath:data/all_talks_data.csv");
        MappingIterator<Map<String, String>> it = mapper
                .readerForMapOf(String.class)
                .with(schema)
                .readValues(file);

        while (it.hasNext()) {
            Map<String, String> line = it.next();
            Metadata metadata = new Metadata();
            metadata.add("talk_url", line.get("talk_url"));
            metadata.add("speakers", line.get("speakers"));
            metadata.add("talk_times", line.get("talk_times"));
            documents.add(Document.from(line.get("Title"), metadata));
        }
        return documents;
    }
}
