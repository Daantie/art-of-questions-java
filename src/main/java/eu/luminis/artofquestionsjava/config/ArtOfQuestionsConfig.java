package eu.luminis.artofquestionsjava.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.huggingface.HuggingFaceModelName.*;
import static dev.langchain4j.model.openai.OpenAiModelName.*;

@Configuration
public class ArtOfQuestionsConfig {
    private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private final String HUGGINGFACEHUB_API_KEY = System.getenv("HUGGINGFACEHUB_API_KEY");

    @Qualifier("openai")
    @Bean
    public ChatLanguageModel openAiChatLanguageModel() {
        String modelName = GPT_3_5_TURBO;
        // TODO: Initiate an openAI chat language model
        return null;
    }

    @Qualifier("huggingFace")
    @Bean
    public ChatLanguageModel huggingFaceChatLanguageModel() {
        String modelId = TII_UAE_FALCON_7B_INSTRUCT;
        // TODO: Initiate a HuggingFace chat language model
        return null;
    }
}
