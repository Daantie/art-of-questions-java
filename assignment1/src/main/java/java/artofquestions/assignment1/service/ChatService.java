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
        String question = "At which city is the Devoxx conference held?";
        PromptTemplate promptTemplate = PromptTemplate.from("Answer the question between triple backticks to the best of your ability. If you don't know the answer, just say that you don't know the answer. ```{{question}}```");
        Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        return promptTemplate.apply(variables);
    }

    public Map<String, String> chat() {
        Prompt prompt = createPrompt();
        LOGGER.info("Created prompt: {}", prompt.text());

        String openaiResponse = openAiModel.sendUserMessage(prompt.toUserMessage()).text();
        String huggingFaceResponse = huggingFaceModel.sendMessages(prompt.toUserMessage()).text();

        Map<String, String> llmResponses = new HashMap<>();
        llmResponses.put(OPENAI_RESPONSE, openaiResponse);
        llmResponses.put(HUGGINGFACE_RESPONSE, huggingFaceResponse);
        return llmResponses;
    }
}
