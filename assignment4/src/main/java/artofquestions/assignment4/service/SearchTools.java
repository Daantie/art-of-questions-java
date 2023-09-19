package artofquestions.assignment4.service;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class SearchTools {
    private final QuestionAnswerService questionAnswerService;

    public SearchTools(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @Tool("Answers FAQ related questions about Devoxx Belgium")
    String searchFaq(String question) {
        return questionAnswerService.answerFaqQuestion(question);
    }

    @Tool("Answers questions related to the talks on the Devoxx Belgium conference")
    String searchTalks(String question) {
        return questionAnswerService.answerTalksQuestion(question);
    }
}
