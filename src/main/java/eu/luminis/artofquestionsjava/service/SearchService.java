package eu.luminis.artofquestionsjava.service;

import java.util.ArrayList;
import java.util.List;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public SearchService(@Qualifier("inMemoryEmbeddingStore") EmbeddingStore<TextSegment> embeddingStore,
                         @Qualifier("openaiEmbedding") EmbeddingModel embeddingModel) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public List<EmbeddingMatch<TextSegment>> search() {
        String question = "Who should I contact to become a sponsor?";
        List<EmbeddingMatch<TextSegment>> answers = new ArrayList<>();

        // TODO: implement the search flow with the embedding store.
        // Some pointers:
        // - Create an embedding for the above question. Use that embedding to search in the embedding store.
        // - Make sure you use the same embedding model as you used for embedding your data!
        // - Convert the search results to a list of strings and return that.

        LOGGER.info("Results for the question: {}", question);
        return answers;
    }
}
