package eu.luminis.artofquestionsjava.service;

import java.util.HashMap;
import java.util.Map;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatLanguageModel openAiModel;
    private final ChatLanguageModel huggingFaceModel;

    public ChatService(@Qualifier("openai") ChatLanguageModel openAiModel,
                       @Qualifier("huggingFace") ChatLanguageModel huggingFaceModel) {
        this.openAiModel = openAiModel;
        this.huggingFaceModel = huggingFaceModel;
    }

    private Prompt createPrompt(String question) {
        // TODO: Extend this prompt template to get a better response from the LLMs
        PromptTemplate promptTemplate = PromptTemplate.from("{question}");
        Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        return promptTemplate.apply(variables);
    }

    public Map<String, String> chat(String question) {
        Prompt prompt = createPrompt(question);

        // TODO: Interact with the LLMs, get their response and add those to the below map

        Map<String, String> llmResponses = new HashMap<>();
        llmResponses.put("openaiResponse", "");
        llmResponses.put("huggingFaceResponse", "");
        return llmResponses;
    }
}
