package eu.luminis.artofquestionsjava.service;

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
        List<EmbeddingMatch<TextSegment>> answers;

        Embedding queryEmbedding = embeddingModel.embed(question);
        answers = embeddingStore.findRelevant(queryEmbedding, 4, 0.75);

        LOGGER.info("Results for the question: {}", question);
        return answers;
    }
}
