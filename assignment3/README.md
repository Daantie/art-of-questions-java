# Putting it all together to implement RAG (Retrieval Augmented Generation)
The title says it all. In this assignment we're going to put all the previous assignments together and implement RAG.

### Task #1
First you need to copy over some of your work again from Assignment 2:
- You can copy over the complete content of the `ArtOfQuestionsConfig` class.
- Do the same for `IngestService`.

## Implementing RAG
### Task #2
Task #2 through #5 are all in the [QuestionAnswerService.java](src/main/java/artofquestions/assignment3/service/QuestionAnswerService.java) class.

At the top of the class, change the prompt template just like you did in Assignment 1 (Remember the `ChatService`?).
Make sure you let the LLM answers the given question from the information/content you provided (the `{{question}}` and `{{information}}` placeholders).

### Task #3
Langchain isn't called Langchain for nothing. It is meant to chain things together. That's what we're going to do here.
Implement the `createRetrievalChain` method. Use the `ConversationalRetrievalChain` class of Langchain4j.
This combines the retriever (for similarity search), a prompt template (instructions for the LLM) and the LLM (to generate an answer from your search results).

### Task #4
In the `generateOpenAiAnswer` method, make the call to the OpenAI LLM and return the response.

### Task #5
In the `generateHuggingFaceAnswer` method, make the call to the HuggingFace LLM and return the response.
Run the project and see the difference between the OpenAI and HuggingFace answers. Which one satisfies you more? Why?

### Task #6
This task is all about the quality of the answers and how to improve them. Always try/verify different questions.

If you're not satisfied by one of the answers, then you need to do some tweaking. Here are some of the things you could try (some of our previous experiments):
- Altering your prompt to give better instructions to the LLM to answer your question.
- Increase or decrease the LLM temperature.
- Change maxTokens (or maxNewTokens for HuggingFace) property.
- Use a different chunk size of your document splitter.


Some more things you could try, but are out of scope for this workshop:
- Switch to a different chat LLM.
- Use a different embedding model.
- Try another vector store.
