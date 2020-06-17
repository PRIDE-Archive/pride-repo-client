package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;

public class FileRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String FILE_URL_PATH = "/files";

    FileRepoClient(ObjectMapper objectMapper, PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = objectMapper;
        this.prideRepoRestClient = prideRepoRestClient;
    }
}
