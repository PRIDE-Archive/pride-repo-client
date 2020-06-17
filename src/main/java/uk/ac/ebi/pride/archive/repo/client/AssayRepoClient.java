package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;

public class AssayRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String ASSAY_URL_PATH = "/assay";

    AssayRepoClient(ObjectMapper objectMapper, PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = objectMapper;
        this.prideRepoRestClient = prideRepoRestClient;
    }
}
