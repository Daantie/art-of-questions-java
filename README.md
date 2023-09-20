# art-of-questions
Repository containing required resources for our workshop titled: The Art of Questions, creating a QA system using semantic search and LLMs

There are two flavours of this workshop. Choose the Python approach, if you want more features and a vibrant ecosystem. Or choose the Java approach, if you are more comfortable with Java. Both languages work for this workshop.

Java is this repository, you can find the pyhon edition here:
[Art of Questions - Python](https://github.com/jettro/art-of-questions)

## Setup the repository
This repository makes use of Maven and Java 17, so make sure your system/environment is able to work with those.

We are integrating with a number of other tools and services. For that, we need some API keys. These should be added as environment variables, so they won't end up in your code repository. You can add them in your IDE run configuration, or you can check this page if you want to add them in your bash environment: https://www3.ntu.edu.sg/home/ehchua/programming/howto/Environment_Variables.html

The environment variables we need for this workshop are as follows:
- OPENAI_API_KEY
- HUGGINGFACEHUB_API_KEY
- UNSTRUCTURED_API_KEY
- WEAVIATE_API_KEY
- WEAVIATE_CLUSTER_URL

### OpenAI API key
Langchain4j offers an easy way to use OpenAI for testing purposes. Just use `demo` as an API key.

If you want to use an API key of your own account follow these instructions. An API key can be created on this page: https://platform.openai.com/account/api-keys. Sign up for an account if you don't have one. Log in if needed. Then create an API key to use.

### HuggingFace API key
To get an API key for HuggingFace you need an account. After creating an account and logging in, an API key (called a token there) can be created here: https://huggingface.co/settings/tokens. Make sure to only create a read token.

### Unstructured API key
For unstructured.io you need to register for an API key. This can be done here: https://unstructured.io/#get-api-key. The API key will be sent to your email. Be sure to check your spam folder if you're not seeing it.

### Weaviate API key and cluster URL
Go to https://console.weaviate.cloud/ to create an account if you don't have one already. Sign in to the Weaviate Cloud Services. Press the `+ Create cluster` button and create a free sandbox cluster. 
Give it an appropriate name, make sure authentication is enabled (default) and press `Create`. When the creation of the cluster is finished (could take a couple minutes), press `Details` to view your cluster details.
Here you can find your cluster URL and your automatically generated API key (press `API keys`).
