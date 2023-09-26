package eu.luminis.artofquestionsjava.service;

import java.util.List;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
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

    public void generateAnswer(String question, List<String> searchResults) {
        // TODO: Write a prompt template to generate an answer to your questions from the search results.

        // TODO: Use your prompt to get a UserMessage class and send that to both chat models.

        // TODO: Log both responses to the console log with the LOGGER on top of this class.
    }
}
