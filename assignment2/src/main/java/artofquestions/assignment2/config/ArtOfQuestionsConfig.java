package artofquestions.assignment2.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
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
        // TODO: Copy over your solution from Assignment 1
        return null;
    }

    @Qualifier("huggingFaceChatModel")
    @Bean
    public ChatLanguageModel huggingFaceChatLanguageModel() {
        // TODO: Copy over your solution from Assignment 1
        return null;
    }

    @Qualifier("openaiEmbedding")
    @Bean
    public EmbeddingModel openAiEmbeddingModel() {
        String modelName = TEXT_EMBEDDING_ADA_002;
        // TODO: Initiate an OpenAI embedding model. Make sure to use the OPENAI_API_KEY property as API key.
        return null;
    }

    @Qualifier("huggingFaceEmbedding")
    @Bean
    public EmbeddingModel huggingFaceEmbeddingModel() {
        String modelId = "sentence-transformers/all-mpnet-base-v2";
        // TODO: Initiate a HuggingFace embedding model. Make sure to use the HUGGINGFACE_API_KEY property as access token.
        return null;
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // TODO: Initiate an embedding store. This could be an in memory store or Weaviate (or others).
        // In memory store is the faster way now (doesn't require a Weaviate cluster), but you need Weaviate for (bonus) assignment 4.
        // When using Weaviate, set objectClass to "FAQ".
        return null;
    }

    @Bean
    public EmbeddingStoreRetriever embeddingStoreRetriever(EmbeddingStore<TextSegment> embeddingStore,
                                                           @Qualifier("openaiEmbedding") EmbeddingModel embeddingModel) {
        // TODO: Initiate an embedding store retriever. Use the embeddingStore and try both the OpenAI or HuggingFace models (change the qualifier name).
        // Also play around with maxResults and minScore and see what influence that has on the results. The minScore should be a number between 0 and 1.
        return null;
    }
}
