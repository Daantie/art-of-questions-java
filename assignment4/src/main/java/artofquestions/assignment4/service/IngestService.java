package artofquestions.assignment4.service;

import java.util.List;

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
    private final EmbeddingStore<TextSegment> faqStore;
    private final EmbeddingStore<TextSegment> talksStore;

    public IngestService(@Qualifier("openaiEmbedding") EmbeddingModel embeddingModel,
                         @Qualifier("faqStore") EmbeddingStore<TextSegment> faqStore,
                         @Qualifier("talksStore") EmbeddingStore<TextSegment> talksStore) {
        this.embeddingModel = embeddingModel;
        this.faqStore = faqStore;
        this.talksStore = talksStore;
    }

    public void ingestFaqDocument(Document document) {
        // TODO: Implement embedding store ingestor which uses the faqStore. Don't forget the document splitter and to ingest the data.
    }

    public void ingestTalksDocuments(List<Document> documentList) {
        // TODO: Implement embedding store ingestor which uses the talksStore. Don't forget the document splitter and to ingest the data.
    }
}
