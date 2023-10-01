package artofquestions.assignment3.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerService {
    private final ChatLanguageModel openaiChatModel;
    private final ChatLanguageModel huggingFaceChatModel;
    private final EmbeddingStoreRetriever retriever;
    // TODO: Try different questions (obviously in a normal system the question comes from some user interface).
    private final String question = "Who should I contact to become a sponsor?";
    //TODO: Change this prompt template so the LLM will answer the question from the information provided.
    private final PromptTemplate promptTemplate = PromptTemplate.from("{{question}} {{information}}");

    public QuestionAnswerService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel,
                                 @Qualifier("huggingFaceChatModel") ChatLanguageModel huggingFaceChatModel,
                                 EmbeddingStoreRetriever retriever) {
        this.openaiChatModel = openaiChatModel;
        this.huggingFaceChatModel = huggingFaceChatModel;
        this.retriever = retriever;
    }

    private ConversationalRetrievalChain createRetrievalChain(ChatLanguageModel chatLanguageModel) {
        // TODO: Initiate and return a ConversationalRetrievalChain with the chatLanguageModel, promptTemplate and retriever.
        return null;
    }

    public String generateOpenAiAnswer() {
        // TODO: create a ConversationalRetrievalChain object with the openaiChatModel, execute the question and return the answer.
        return null;
    }

    public String generateHuggingFaceAnswer() {
        // TODO: create a ConversationalRetrievalChain object with the huggingFaceChatModel, execute the question and return the answer.
        return null;
    }
}
