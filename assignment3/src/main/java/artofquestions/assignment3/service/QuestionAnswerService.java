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
    private final String question = "Who should I contact to become a sponsor?";

    public QuestionAnswerService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel,
                                 @Qualifier("huggingFaceChatModel") ChatLanguageModel huggingFaceChatModel,
                                 EmbeddingStoreRetriever retriever) {
        this.openaiChatModel = openaiChatModel;
        this.huggingFaceChatModel = huggingFaceChatModel;
        this.retriever = retriever;
    }

    private PromptTemplate createPromptTemplate() {
        return PromptTemplate.from("Answer the question between triple backticks with the information provided between triple quotes to the best of your ability. " +
                "If you don't know the answer, just say you don't know the answer. ```{{question}}``` \"\"\"{{information}}\"\"\"");
    }

    private ConversationalRetrievalChain createRetrievalChain(ChatLanguageModel chatLanguageModel) {
        return ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .promptTemplate(createPromptTemplate())
                .retriever(retriever)
                .build();
    }

    public String generateOpenAiAnswer() {
        ConversationalRetrievalChain retrievalChain = createRetrievalChain(openaiChatModel);
        return retrievalChain.execute(question);
    }

    public String generateHuggingFaceAnswer() {
        ConversationalRetrievalChain retrievalChain = createRetrievalChain(huggingFaceChatModel);
        return retrievalChain.execute(question);
    }
}
