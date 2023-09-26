# Putting it all together to implement RAG (Retrieval Augmented Generation)
The title says it all. In this assignment we're going to put all the previous assignments together and implement RAG.

## Data parsing with Unstructured
### Task 1
Langchain4j has a few document parser classes, like `TextDocumentParser`, `MsOfficeDocumentParser` and `PdfDocumentParser`. 
You can use these to parse those types of documents into strings. We've prepared a [PDF document](src/main/resources/data/faq.pdf) of the FAQ page from the conference website.
The problem is the PDF document doesn't contain text, only images. The `PdfDocumentParser` has no OCR support, so unfortunately we can't use that.
Instead, we will make use of the Unstructured.io API which is free to use and does have OCR capabilities. Hence you need a Unstructured.io API key.

You can find the API documentation here: https://unstructured-io.github.io/unstructured/api.html.

In the [DocumentParseService](src/main/java/eu/luminis/artofquestionsjava/service/DocumentParseService.java), implement the call to the Unstructured.io API and return the content of the PDF as a String.

## Ingesting data into store
### Task #2
First, the text needs to be vectorized. For that we need an embedding model. Initialize the OpenAI embedding model in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
Make use of the langchain4j `OpenAiEmbeddingModel` class and its builder method.

### Task #3
Second, we need a place to store the vectors so they can be used for semantic search. Initialize the in memory vector store in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
Make use of the langchain4j `InMemoryEmbeddingStore` class. This should be as easy as just returning a new instance.

### Task #4
Now we need some code to actually let the embedding model transform the text into vectors and ingest those vectors into the vector store.
For this, langchain4j has the `EmbeddingStoreIngestor` class. This also has a builder method.
The complete document text is too large to match similar queries. Therefore, we create chunks of text to be able to get more specific results.
Too large documents cannot be passed to an LLM because of token limitations. Also sending larger pieces of text costs more, since you pay per token.
For this we can use the `DocumentSplitter` class of langchain4j which can be passed to the ingestor, which then takes care of the chunking for us.
Implement the `ingestText` method in the [IngestService](src/main/java/eu/luminis/artofquestionsjava/service/IngestService.java) class. 

## Semantic Search
### Task #5
Cool! We have everything in place to do some semantic search. Implement the `search` method in the [SearchService](src/main/java/eu/luminis/artofquestionsjava/service/SearchService.java) class.
We've already added a question there to showcase, but feel free to try your own questions too. Don't forget to make an embedding for your question when querying!
Use the `findRelevant` method of the EmbeddingStore class to search for semantically similar results. By default it returns 10 results, but usually we don't need that much. Setting the `maxResults` property to 4 should suffice for now.
The answers are printed with their score. It's best practice to implement a cutoff to omit results that are not really relevant. Finding the ideal cutoff differs per embedding model, indexed data and questions asked.
Watch the scores for different questions and experiment with the `minScore` property of the embedding model `findRelevant` method.
We're changing the embedding store and model in the next tasks which also changes the scores, so best to not use `minScore` option in the below tasks (or try adding it in the very end).

### Task 6
It's important to choose the most fitting chunk size for your specific use case. Start by playing around with the chunk size which you implemented in task #4 and notice what happens with your results.

### Task 7
It's also important to choose the right embedding model. We've implemented the use of an OpenAI embedding model in task #2. Now let's try a HuggingFace model.
The OpenAI ADA-002 model creates 1536-dimensional embedding vectors and the HuggingFace all-mpnet-base-v2 model creates 768-dimensional embedding vectors.
In [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java), initiate the HuggingFace model in the bean with `@Qualifier("huggingFaceEmbedding")` above it. Make use of the langchain4j `HuggingFaceEmbeddingModel` model and its builder method.
Next, in the constructors of the [IngestService](src/main/java/eu/luminis/artofquestionsjava/service/IngestService.java) and [SearchService](src/main/java/eu/luminis/artofquestionsjava/service/SearchService.java) classes, change the qualifier of the `embeddingModel` property from `openaiEmbedding` to `huggingFaceEmbedding`.
Now run the project and check if there are any differences.

## Task 8
It's also important to use the right vector store for your use case. Langchain4j has support for some of those next to the in memory embedding store (which is not recommended for production use).
As you might have guessed by the setup for the URL and API keys, we're going to implement the use of Weaviate.
In [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java), initiate the Weaviate embedding store in the bean with `@Qualifier("weaviateEmbeddingStore")` above it. Make use of the langchain4j `WeaviateEmbeddingStore` model and its builder method.
Run the project and check the results. You can also check your Weaviate cluster if the results are indexed there and what the embeddings look like.
