package eu.luminis.artofquestionsjava.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.openai.OpenAiModelName.*;

@Configuration
public class ArtOfQuestionsConfig {
    private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private final String HUGGINGFACE_API_KEY = System.getenv("HUGGINGFACE_API_KEY");
    private final String WEAVIATE_API_KEY = System.getenv("WEAVIATE_API_KEY");
    private final String WEAVIATE_URL = System.getenv("WEAVIATE_URL");

    @Qualifier("openaiEmbedding")
    @Bean
    public EmbeddingModel openAiEmbeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(OPENAI_API_KEY)
                .modelName(TEXT_EMBEDDING_ADA_002)
                .build();
    }

    @Qualifier("inMemoryEmbeddingStore")
    @Bean
    public EmbeddingStore<TextSegment> inMemoryEmbeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Qualifier("huggingFaceEmbedding")
    @Bean
    public EmbeddingModel huggingFaceEmbeddingModel() {
        return HuggingFaceEmbeddingModel.builder()
                .accessToken(HUGGINGFACE_API_KEY)
                .modelId("sentence-transformers/all-mpnet-base-v2")
                .build();
    }

    @Qualifier("weaviateEmbeddingStore")
    @Bean
    public EmbeddingStore<TextSegment> weaviateEmbeddingStore() {
        return WeaviateEmbeddingStore.builder()
                .apiKey(WEAVIATE_API_KEY)
                .scheme("https")
                .host(WEAVIATE_URL)
                .build();
    }

    @Qualifier("openaiChatModel")
    @Bean
    public ChatLanguageModel openaiChatModel() {
        // TODO: Initiate the OpenAI chat LLM
        return null;
    }

    @Qualifier("huggingFaceChatModel")
    @Bean
    public ChatLanguageModel huggingFaceChatModel() {
        // TODO: Initiate the HuggingFace chat LLM
        return null;
    }
}
