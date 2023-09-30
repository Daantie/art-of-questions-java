package artofquestions.assignment4.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FunctionCallService {

    private final Assistant assistant;

    public FunctionCallService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel, SearchTools searchTools) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(openaiChatModel)
                .tools(searchTools)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    interface Assistant {
        @SystemMessage({
                "You are a support chatbot of a conference named 'Devoxx Belgium'.",
                "Choose the correct function to execute depending on the question asked."
        })
        String chat(String userMessage);
    }

    public String callFunction(String question) {
        return assistant.chat(question);
    }
}
