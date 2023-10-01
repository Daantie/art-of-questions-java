# Data Parsing & Semantic (Vector) Search
This assignment is about parsing data, vectorizing your text and ingesting your vectors (or embeddings) into a vector/embedding store.
Next to that we're also going to perform semantic search, where we're going to search for semantically similar texts.

For this assignment, make sure you have all the environment variables set ([see root README](../README.md)).

### Task #1
Copy over your solution in the `ArtOfQuestionsConfig` from Assignment 1 to [this assignment's ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java)

## Data parsing with Unstructured
### Task #2
Langchain4j has a few document parser classes, like `TextDocumentParser`, `MsOfficeDocumentParser` and `PdfDocumentParser`.
You can use these to parse those types of documents into strings. We've prepared a [PDF document](src/main/resources/data/faq.pdf) of the FAQ page from the conference website.
The problem is the PDF document doesn't contain text, only images. The `PdfDocumentParser` has no OCR support, so unfortunately we can't use that.
Instead, we will make use of the Unstructured.io API which is free to use and does have OCR capabilities. Hence, you need a Unstructured.io API key.

You can find the API documentation here: https://unstructured-io.github.io/unstructured/api.html.

We've already implemented the code for this. You can check this out in [DocumentParseService](src/main/java/eu/luminis/artofquestionsjava/service/DocumentParseService.java).

## Ingesting data into store
### Task #3
First, the text needs to be vectorized. For that we need an embedding model. Initialize the OpenAI embedding model in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
Make use of the langchain4j `OpenAiEmbeddingModel` class and its builder method.

### Task #4
Second, we need a place to store the vectors, so they can be used for semantic search. Initialize the in memory vector store in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
Make use of the langchain4j `InMemoryEmbeddingStore` class. This should be as easy as just returning a new instance.

### Task #5
Now we need some code to actually let the embedding model transform the text into vectors and ingest those vectors into the vector store.
For this, langchain4j has the `EmbeddingStoreIngestor` class. This also has a builder method.
The complete document text is too large to match similar queries. Therefore, we create chunks of text to be able to get more specific results.
Too large documents cannot be passed to an LLM because of token limitations. Also sending larger pieces of text costs more, since you pay per token.
For this we can use the `DocumentSplitter` class of langchain4j which can be passed to the ingestor, which then takes care of the chunking for us.
Implement the `ingestDocument` method in the [IngestService](src/main/java/eu/luminis/artofquestionsjava/service/IngestService.java) class.

### Task #6
The last thing we need to be able to easily perform semantic search is something to retrieve results from the store. Langchain4j has the `EmbeddingStoreRetriever` class to do precisely that.
Initialize that class in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java) with the embedding model and store.

## Semantic Search
### Task #7
Cool! We have everything in place to do some semantic search. Implement the `search` method in the [SearchService](src/main/java/eu/luminis/artofquestionsjava/service/SearchService.java) class.
We've already added a question there to showcase, but feel free to try your own questions too.
When you perform vector search you always need to create an embedding of your question with the same embedding model used for embedding your content.
The embedding of your question will then be used to find documents with similar meaning. The `EmbeddingStoreRetriever` of the previous task takes care of that for you.
Use the `findRelevant` method of the `EmbeddingStoreRetriever` class to search for semantically similar results.

### Task #8
It's important to choose the most fitting chunk size for your specific use case. Start by playing around with the chunk size and overlap which you implemented in task #4 and examine what happens with your results.

### Task #9
It's also important to choose the right embedding model. We've implemented the use of an OpenAI embedding model in task #2. Now let's try a HuggingFace model.
The OpenAI ADA-002 model creates 1536-dimensional embedding vectors and the HuggingFace all-mpnet-base-v2 model creates 768-dimensional embedding vectors.
In [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java), initiate the HuggingFace model in the bean with `@Qualifier("huggingFaceEmbedding")` above it. Make use of the langchain4j `HuggingFaceEmbeddingModel` model and its builder method.
Next, in the constructors of the [IngestService](src/main/java/eu/luminis/artofquestionsjava/service/IngestService.java) class and in the `EmbeddingStoreRetriever` bean in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java), change the qualifier of the `embeddingModel` property from `openaiEmbedding` to `huggingFaceEmbedding`.
Now run the project and check if there are any differences.

## Task #10
It's also important to use the right vector store for your use case. Langchain4j has support for some of those next to the in memory embedding store (which is not recommended for production use).
As you might have guessed by the setup for the URL and API keys, we're going to implement the use of Weaviate.
In [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java), replace the in memory store with the Weaviate embedding store in the `EmbeddingStore<TextSegment>` bean. Make use of the langchain4j `WeaviateEmbeddingStore` class and its builder method.
Run the project and check the results. You can also check your Weaviate cluster if the results are indexed there and what the embeddings look like.
Run the below query on your cluster:
```
{
  Get {
    FAQ {
      text
    }
  }
}
```
