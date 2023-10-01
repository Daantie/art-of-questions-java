package artofquestions.assignment2.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IngestService {
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public IngestService(@Qualifier("openaiEmbedding") EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    public void ingestDocument(Document document) {
        // TODO: Implement the ingestion part with the use of the EmbeddingStoreIngestor class
        // Some pointers:
        // - The EmbeddingStoreIngestor class has a builder method.
        // - Pass it a DocumentSplitter object with a segment size of 300 and overlap of 100 (this is for chunking).
        // - Also pass the model and store. Change the @Qualifier name in the constructor if you want to change the model.
        // - Use the ingestor to ingest the Document object
    }
}
