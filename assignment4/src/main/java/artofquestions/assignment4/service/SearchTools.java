package artofquestions.assignment4.service;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class SearchTools {
    private final QuestionAnswerService questionAnswerService;

    public SearchTools(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    // TODO: add tools here that call the answerFaqQuestion and answerTalksQuestion methods of the questionAnswerService.
    // You can use this example as inspiration:
    // https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/ServiceWithToolsExample.java
}
