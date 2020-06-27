package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.assay.Assay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AssayRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String ASSAY_URL_PATH = "/assay";

    AssayRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<Assay> findById(Long assayId) throws IOException {
        final String url = ASSAY_URL_PATH + "/findById/{assayId}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("assayId", assayId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        Assay assay = objectMapper.readValue(response, Assay.class);
        return Optional.ofNullable(assay);
    }

    public Assay findByAccession(String assayAccession) throws IOException {
        final String url = ASSAY_URL_PATH + "/findByAccession/{assayAccession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("assayAccession", assayAccession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        Assay assay = objectMapper.readValue(response, Assay.class);
        return assay;
    }

    public List<Assay> findAllByProjectId(Long projectId) throws IOException {
        final String url = ASSAY_URL_PATH + "/findAllByProjectId/{projectId}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("projectId", projectId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<Assay> assays = objectMapper.readValue(response, List.class);
        return assays;
    }

    public List<Assay> findAllByProjectAccession(String projectAccession) throws IOException {
        final String url = ASSAY_URL_PATH + "/findAllByProjectAccession/{projectAccession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("projectAccession", projectAccession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<Assay> assays = objectMapper.readValue(response, List.class);
        return assays;
    }

    public Long countByProjectAccession(String projectAccession) throws IOException {
        final String url = ASSAY_URL_PATH + "/countByProjectAccession/{projectAccession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("projectAccession", projectAccession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        Long count = objectMapper.readValue(response, Long.class);
        return count;
    }

    public Assay save(Assay assay) throws JsonProcessingException {
        final String url = ASSAY_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(assay);
        String response = prideRepoRestClient.sendPostRequest(url, payload);

        return objectMapper.readValue(response, Assay.class);
    }
}
