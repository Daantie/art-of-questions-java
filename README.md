# Working with LLMs
This assignment is all about interacting with Large Language Models (LLMs). You can try HuggingFace or OpenAI. Running this part from your local machine is a bit harder. The models are called Large language Models for a reason.
From now on, you will use Langchain4j to talk to large language models.

For this assignment, make sure you have at least the OpenAI and HuggingFace API keys environment variables set.

### Task #1
Initiate the LLMs in the [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java) configuration class.

Try using
[OpenAiChatModel](https://github.com/langchain4j/langchain4j/blob/main/langchain4j/src/main/java/dev/langchain4j/model/openai/OpenAiChatModel.java)
and
[HuggingFaceChatModel](https://github.com/langchain4j/langchain4j/blob/main/langchain4j/src/main/java/dev/langchain4j/model/huggingface/HuggingFaceChatModel.java)
classes and their builder methods to do that. Use the model name/id already given in the beans.

### Task #2
Change the prompt template in the [ChatService](src/main/java/eu/luminis/artofquestionsjava/service/ChatService.java) class to improve the responses of the LLM.

### Task #3
Interact with the LLMs in the [ChatService](src/main/java/eu/luminis/artofquestionsjava/service/ChatService.java) class and return their responses.

### Task #4
Run the project and use the web interface at http://localhost:8080/chat to start using the LLMs to answer your questions.

### Task #5
Try changing the temperature of your LLMs in the [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java) configuration class. Re-run your application afterwards and observe what happens to the responses.
You could also try to change the max(New)Tokens for the LLMs and see what happens.

### Task 6
Try changing your prompt template in the [ChatService](src/main/java/eu/luminis/artofquestionsjava/service/ChatService.java) class so it returns a list when asking which cities the conference is held.

### Task 7
Try changing your prompt template in the [ChatService](src/main/java/eu/luminis/artofquestionsjava/service/ChatService.java) class so it returns JSON format. Make it return objects which contain the country and the city when asking which cities the conference is held.
