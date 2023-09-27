package java.artofquestions.assignment3.service;

import java.util.List;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionAnswerService.class);
    private ChatLanguageModel openaiChatModel;
    private ChatLanguageModel huggingFaceChatModel;

    public QuestionAnswerService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel,
                                 @Qualifier("huggingFaceChatModel") ChatLanguageModel huggingFaceChatModel) {
        this.openaiChatModel = openaiChatModel;
        this.huggingFaceChatModel = huggingFaceChatModel;
    }

    private PromptTemplate createPrompt(String question, List<String> searchResults) {
        // TODO: Write a prompt template to generate an answer to your questions from the search results.
        // Make sure to use {{question}} and {{information}} for your variables
        return null;
    }

    public String generateOpenAiAnswer(String question, List<String> searchResults) {
        Prompt prompt = createPrompt(question, searchResults);
        // TODO: Use your prompt to get a UserMessage class and send that to the OpenAI model.

        // TODO: Return the response as a String.
        return null;
    }

    public String generateHuggingFaceAnswer(String question, List<String> searchResults) {
        Prompt prompt = createPrompt(question, searchResults);
        // TODO: Use your prompt to get a UserMessage class and send that to the OpenAI model.

        // TODO: Return the response as a String.
        return null;
    }
}
