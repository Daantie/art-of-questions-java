package artofquestions.assignment4.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FunctionCallService {

    // TODO: Implement an AI service that uses the openaiChatModel and the searchTools to decide which functions (or tools) to use.
    // The callFunction method should return the answer to the question.
    // You can use this example as a reference:
    // https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/ServiceWithToolsExample.java

    public FunctionCallService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel, SearchTools searchTools) {

    }

    public String callFunction(String question) {
        return null;
    }
}
