package artofquestions.assignment4.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.huggingface.HuggingFaceModelName.*;
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
                .temperature(0.2)
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
                .modelId(TII_UAE_FALCON_7B_INSTRUCT)
                .timeout(ofSeconds(15))
                .temperature(0.7)
                .maxNewTokens(200)
                .build();
    }

    @Qualifier("openaiEmbedding")
    @Bean
    public EmbeddingModel openAiEmbeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(OPENAI_API_KEY)
                .modelName(TEXT_EMBEDDING_ADA_002)
                .build();
    }

    @Qualifier("faqStore")
    @Bean
    public EmbeddingStore<TextSegment> faqEmbeddingStore() {
        return WeaviateEmbeddingStore.builder()
                .apiKey(WEAVIATE_API_KEY)
                .scheme("https")
                .objectClass("FAQ")
                .host(WEAVIATE_URL)
                .build();
    }

    @Qualifier("talksStore")
    @Bean
    public EmbeddingStore<TextSegment> talksEmbeddingStore() {
        return WeaviateEmbeddingStore.builder()
                .apiKey(WEAVIATE_API_KEY)
                .scheme("https")
                .objectClass("Talks")
                .host(WEAVIATE_URL)
                .build();
    }

    @Qualifier("faqRetriever")
    @Bean
    public EmbeddingStoreRetriever faqRetriever(@Qualifier("faqStore") EmbeddingStore<TextSegment> embeddingStore) {
        return EmbeddingStoreRetriever.from(embeddingStore, openAiEmbeddingModel(), 4);
    }

    @Qualifier("talksRetriever")
    @Bean
    public EmbeddingStoreRetriever talksRetriever(@Qualifier("talksStore") EmbeddingStore<TextSegment> embeddingStore) {
        return EmbeddingStoreRetriever.from(embeddingStore, openAiEmbeddingModel(), 4);
    }
}
