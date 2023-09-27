# Putting it all together to implement RAG (Retrieval Augmented Generation)
The title says it all. In this assignment we're going to put all the previous assignments together and implement RAG.

Task 1 to 3 will be in the [QuestionAnswerService](src/main/java/eu/luminis/artofquestionsjava/service/QuestionAnswerService.java) class.

## Implementing RAG
### Task 1
In the `createPrompt` method, create a prompt template just like you did in assignment 1 (Look back at the [ChatService](src/main/java/eu/luminis/artofquestionsjava/service/ChatService.java) class).
Make sure you let the LLM answers the given question from the information/content you provided.

### Task #2
In the `generateOpenAiAnswer` method, make the call to the OpenAI LLM and return the response (again, revisit ChatService).
Run the project and see what answer it produced.

### Task #3
In the `generateHuggingFaceAnswer` method, make the call to the HuggingFace LLM and return the response.
Run the project and see the difference between the OpenAI and HuggingFace answers. Which one satisfies you more? Why?

### Task #4
This task is all about the quality of the answers and how to improve them. Always try/verify different questions.

If you're not satisfied by one of the answers, then you need to do some tweaking. Here are some of the things you could try (some of our previous experiments):
- Altering your prompt to give better instructions to the LLM to answer your question.
- Change the LLM temperature.
- Change maxTokens (maxNewTokens for HuggingFace) property.
- Increase or decrease the chunk size of your document splitter.


Some more things you could try, but are out of scope for this workshop:
- Switch to a different chat LLM.
- Use a different embedding model.
