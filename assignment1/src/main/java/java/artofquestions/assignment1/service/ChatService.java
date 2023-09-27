package java.artofquestions.assignment1.service;

import java.util.HashMap;
import java.util.Map;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.artofquestions.assignment1.Assignment1Application.*;

@Service
public class ChatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
    private final ChatLanguageModel openAiModel;
    private final ChatLanguageModel huggingFaceModel;

    public ChatService(@Qualifier("openaiChatModel") ChatLanguageModel openAiModel,
                       @Qualifier("huggingFaceChatModel") ChatLanguageModel huggingFaceModel) {
        this.openAiModel = openAiModel;
        this.huggingFaceModel = huggingFaceModel;
    }

    private Prompt createPrompt() {
        // TODO: Change this prompt template to get a better response from the LLMs
        String question = "At which city is the Devoxx conference held?";
        PromptTemplate promptTemplate = PromptTemplate.from("Answer this question: {{question}}");
        Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        return promptTemplate.apply(variables);
    }

    public Map<String, String> chat() {
        Prompt prompt = createPrompt();
        LOGGER.info("Created prompt: {}", prompt.text());
        // TODO: Interact with the LLMs, get their response and add those to the below map

        Map<String, String> llmResponses = new HashMap<>();
        llmResponses.put(OPENAI_RESPONSE, "");
        llmResponses.put(HUGGINGFACE_RESPONSE, "");
        return llmResponses;
    }
}
