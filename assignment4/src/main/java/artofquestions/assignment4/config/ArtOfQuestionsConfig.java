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
        // TODO: Copy over from any previous assignment
        return null;
    }

    @Qualifier("openaiEmbedding")
    @Bean
    public EmbeddingModel openAiEmbeddingModel() {
        // TODO: Copy over from any previous assignment
        return null;
    }

    @Qualifier("faqStore")
    @Bean
    public EmbeddingStore<TextSegment> faqEmbeddingStore() {
        // TODO: Initiate a weaviate embedding store with objectClass "FAQ"
        return null;
    }

    @Qualifier("talksStore")
    @Bean
    public EmbeddingStore<TextSegment> talksEmbeddingStore() {
        // TODO: Initiate a weaviate embedding store with objectClass "Talks"
        return null;
    }

    @Qualifier("faqRetriever")
    @Bean
    public EmbeddingStoreRetriever faqRetriever(@Qualifier("faqStore") EmbeddingStore<TextSegment> embeddingStore) {
        // TODO: Initiate an embedding store retriever for the FAQ store
        return null;
    }

    @Qualifier("talksRetriever")
    @Bean
    public EmbeddingStoreRetriever talksRetriever(@Qualifier("talksStore") EmbeddingStore<TextSegment> embeddingStore) {
        // TODO: Initiate an embedding store retriever for the talks store
        return null;
    }
}
