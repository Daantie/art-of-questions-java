package java.artofquestions.assignment2.config;

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
import static java.time.Duration.*;

@Configuration
public class ArtOfQuestionsConfig {
    private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private final String HUGGINGFACE_API_KEY = System.getenv("HUGGINGFACE_API_KEY");
    private final String WEAVIATE_API_KEY = System.getenv("WEAVIATE_API_KEY");
    private final String WEAVIATE_URL = System.getenv("WEAVIATE_URL");

    @Qualifier("openaiChatModel")
    @Bean
    public ChatLanguageModel openAiChatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(OPENAI_API_KEY)
                .modelName(GPT_3_5_TURBO)
                .temperature(0.7)
                .timeout(ofSeconds(15))
                .maxRetries(3)
                .logResponses(true)
                .logRequests(true)
                .build();
    }

    @Qualifier("huggingFaceChatModel")
    @Bean
    public ChatLanguageModel huggingFaceChatLanguageModel() {
        return HuggingFaceChatModel.builder()
                .accessToken(HUGGINGFACE_API_KEY)
                .modelId("sentence-transformers/all-mpnet-base-v2")
                .timeout(ofSeconds(15))
                .temperature(0.7)
                .build();
    }

    @Qualifier("openaiEmbedding")
    @Bean
    public EmbeddingModel openAiEmbeddingModel() {
        String modelName = TEXT_EMBEDDING_ADA_002;
        // TODO: Initiate an openAI embedding model. Make sure to use the OPENAI_API_KEY property as API key.
        return null;
    }

    @Qualifier("inMemoryEmbeddingStore")
    @Bean
    public EmbeddingStore<TextSegment> inMemoryEmbeddingStore() {
        // TODO: Initiate an in memory embedding store.
        return null;
    }


    @Qualifier("huggingFaceEmbedding")
    @Bean
    public EmbeddingModel huggingFaceEmbeddingModel() {
        String modelId = "sentence-transformers/all-mpnet-base-v2";
        // TODO: Initiate a HuggingFace embedding model. Make sure to use the HUGGINGFACE_API_KEY property as access token.
        return null;
    }

    @Qualifier("weaviateEmbeddingStore")
    @Bean
    public EmbeddingStore<TextSegment> weaviateEmbeddingStore() {
        // TODO: Initiate a Weaviate embedding store. Make sure to use the WEAVIATE_API_KEY property as API key and WEAVIATE_URL as host.
        return null;
    }
}
