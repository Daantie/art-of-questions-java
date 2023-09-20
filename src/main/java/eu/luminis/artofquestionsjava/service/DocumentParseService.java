package eu.luminis.artofquestionsjava.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class DocumentParseService {

    private final String UNSTRUCTURED_API_KEY = System.getenv("UNSTRUCTURED_API_KEY");

    public String parseDocument() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:data/faq.pdf");
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        // TODO: Call the Unstructured.io API and convert the response into a single String.
        // Some pointers:
        // - Make sure to include the API key in the headers.
        // - The API accepts multipart/form-data.
        // - Use 'strategy=ocr_only' and 'ocr_languages=eng' in your form data.
        // - The API returns an array of JSON objects, for each object you need to get the "text" property.
        // - Concatenate all text into one String and return that.

        return null;
    }
}
