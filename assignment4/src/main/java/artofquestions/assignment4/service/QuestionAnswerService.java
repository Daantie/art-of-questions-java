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
    private final ConversationalRetrievalChain faqRetrievalChain;
    private final ConversationalRetrievalChain talksRetrievalChain;
    // TODO: Add your awesome prompt here again (maybe copy over from previous assignment)
    private final PromptTemplate promptTemplate = PromptTemplate.from("{{question}} {{information}}");

    public QuestionAnswerService(@Qualifier("openaiChatModel") ChatLanguageModel openaiChatModel,
                                 @Qualifier("faqRetriever") EmbeddingStoreRetriever faqRetriever,
                                 @Qualifier("talksRetriever") EmbeddingStoreRetriever talksRetriever) {

        // TODO: Initiate a ConversationalRetrievalChain for FAQ question answering
        faqRetrievalChain = null;

        // TODO: Initiate a ConversationalRetrievalChain for Talks question answering
        talksRetrievalChain = null;
    }

    public String answerFaqQuestion(String question) {
        // TODO: execute the question with the FAQ chain
        return null;
    }

    public String answerTalksQuestion(String question) {
        // TODO: execute the question with the Talks chain
        return null;
    }
}
