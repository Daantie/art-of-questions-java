package eu.luminis.artofquestionsjava.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IngestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngestService.class);
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public IngestService(@Qualifier("openaiEmbedding") EmbeddingModel embeddingModel,
                         @Qualifier("inMemoryEmbeddingStore") EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    public void ingestText(String text) {
        // TODO: Implement the ingestion part with the use of the EmbeddingStoreIngestor class
        // Some pointers:
        // - The EmbeddingStoreIngestor class has a builder method
        // - Pass it a DocumentSplitter object with a maxSegmentSizeInChars of 300
        // - Also pass the model and store
        // - Create a Document object from the text String and use the ingestor to ingest that Document object
    }
}
