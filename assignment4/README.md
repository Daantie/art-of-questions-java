# LLM Functions
In this last assignment we're going even a step further. We're going to use the reasoning capability of an LLM to choose the right function (or tool) based on the question of the user.
We've added some new data to ingest, the titles of all Devoxx talks! With this data we can answer questions about the talks.
The parsing of these talks is already implemented. The ingestion is still up to you.
Depending on the question, we're going to let the LLM decide to either use FAQ data or Talks data to answer the question.

## Config
### Task #1
In the [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java) we're setting up the OpenAI chat model and embedding model again. You can copy these from a previous assignment.

### Task #2
Initiate the embedding stores and retrievers for both FAQ and Talks in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
You can peek at your previous assignments for how to do this.

## Ingestion
### Task #3
Both the FAQ and the Talks data need to be ingested into their respective embedding stores.
In the [IngestService](src%2Fmain%2Fjava%2Fartofquestions%2Fassignment4%2Fservice%2FIngestService.java), initiate the embedding store ingestors and ingest the data.

## Setting up retrieval chains
### Task #4
In the [QuestionAnswerService](src/main/java/artofquestions/assignment3/service/QuestionAnswerService.java) class, add your cool prompt template again.

### Task #5
Initiate 2 retrieval chains in the constructor of the [QuestionAnswerService](src/main/java/artofquestions/assignment3/service/QuestionAnswerService.java) class. For each chain, use their respective retriever.

### Task #6
Now implement the `answerFaqQuestion` and `answerTalksQuestion` methods by executing the question with the respective chain and returning the answer.

## Tools
### Task #7
Add 2 tools to the [SearchTools](src%2Fmain%2Fjava%2Fartofquestions%2Fassignment4%2Fservice%2FSearchTools.java) class.
One tool should call the `answerFaqQuestion` method and the other the `answerTalksQuestion` method of the [QuestionAnswerService](src/main/java/artofquestions/assignment3/service/QuestionAnswerService.java) class
[Check this link for an example of Tools](https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/ServiceWithToolsExample.java).

## AI Service
### Task #8
Now to implement [FunctionCallService](src%2Fmain%2Fjava%2Fartofquestions%2Fassignment4%2Fservice%2FFunctionCallService.java). This service should initiate an AiService and call this service in the `callFunction` method with the question and return the answer.
[An example can be found here](https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/ServiceWithToolsExample.java).
