package artofquestions.assignment4.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionAnswerService.class);
    private final ConversationalRetrievalChain faqRetrievalChain;
    private final ConversationalRetrievalChain talksRetrievalChain;
    private final PromptTemplate promptTemplate = PromptTemplate.from("Answer the following question to the best of your ability: {{question}}\n" +
            "\n" +
            "Base your answer on the following information:\n" +
            "{{information}}");

    public QuestionAnswerService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel,
                                 @Qualifier("faqRetriever") EmbeddingStoreRetriever faqRetriever,
                                 @Qualifier("talksRetriever") EmbeddingStoreRetriever talksRetriever) {
        faqRetrievalChain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(openaiChatModel)
                .promptTemplate(promptTemplate)
                .retriever(faqRetriever)
                .build();

        talksRetrievalChain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(openaiChatModel)
                .promptTemplate(promptTemplate)
                .retriever(talksRetriever)
                .build();
    }

    public String answerFaqQuestion(String question) {
        LOGGER.info("-------- Answering FAQ question: {} --------", question);
        return faqRetrievalChain.execute(question);
    }

    public String answerTalksQuestion(String question) {
        LOGGER.info("-------- Answering Talks question: {} --------", question);
        return talksRetrievalChain.execute(question);
    }
}
