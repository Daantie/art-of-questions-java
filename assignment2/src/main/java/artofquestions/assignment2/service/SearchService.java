package artofquestions.assignment2.service;

import java.util.List;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final EmbeddingStoreRetriever retriever;

    public SearchService(EmbeddingStoreRetriever retriever) {
        this.retriever = retriever;
    }

    public List<TextSegment> search() {
        String question = "Who should I contact to become a sponsor?";
        // TODO: Use the retriever to find chunks relevant to the question and return those.
        return null;
    }
}
