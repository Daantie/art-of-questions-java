# Data Parsing & Semantic (Vector) Search
This assignment is about parsing data, vectorizing your text and ingesting your vectors (or embeddings) into a vector/embedding store.
Next to that we're also going to perform semantic search, where we're going to search for semantically similar texts.

For this assignment, make sure you have all the environment variables set.

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
Second, we need a place to store the vectors so they can be used for search. Initialize the in memory vector store in [ArtOfQuestionsConfig](src/main/java/eu/luminis/artofquestionsjava/config/ArtOfQuestionsConfig.java).
Make use of the langchain4j `InMemoryEmbeddingStore` class. This should be as easy as just returning a new instance.

### Task #4
Now we need some code to actually let the embedding model transform the text into vectors and ingest those vectors into the vector store.
For this, langchain4j has the `EmbeddingStoreIngestor` class. This also has a builder method.
The complete document text is too large to match similar queries. Therefore, we create chunks of knowledge to be more specific. Too large documents cannot be passed to an LLM.
For this we can use the `DocumentSplitter` class of langchain4j which can be passed to the ingestor, which then takes care of the chunking for us.

## Semantic Search
### Task #5

### Task 6

### Task 7

## Different model and vector store
The OpenAI ADA-002 model creates 1536-dimensional embedding vectors and the HuggingFace all-mpnet-base-v2 model creates 768-dimensional embedding vectors.
