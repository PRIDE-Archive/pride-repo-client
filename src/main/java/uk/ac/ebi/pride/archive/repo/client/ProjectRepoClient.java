package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.models.project.Project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ProjectRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String PROJECT_URL_PATH = "/project";

    ProjectRepoClient(ObjectMapper objectMapper, PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = objectMapper;
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<Project> findById(Long id) throws IOException {
        final String url = PROJECT_URL_PATH + "/findById/{id}";
        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, params);
//        System.out.println(response);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        Project project = objectMapper.readValue(response, Project.class);
        return Optional.ofNullable(project);
    }
//
//
//    public ResponseEntity<String> count() {
//        final String url = "/count";
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public ResponseEntity<String> getAllAccessions() {
//        final String url = "/getAllAccessions";
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public ResponseEntity<String> getAllPublicAccessions() {
//        final String url = "/getAllPublicAccessions";
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public ResponseEntity<String> findByAccession(String accession) {
//
//        final String url = "/project/findByAccession/{accession}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("accession", accession);
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findByAccessionSummary(String accession) {
//
//        final String url = "/findByAccessionSummary/{accession}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("accession", accession);
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findBySubmitterIdAndIsPublic(Long submitterId, Boolean isPublic) {
//        final String url = "/findBySubmitterIdAndIsPublic";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("submitterId", submitterId.toString());
//        params.put("isPublic", isPublic.toString());
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public ResponseEntity<String> findBySubmitterIdAndIsPublicSummary(Long submitterId, Boolean isPublic) {
//        final String url = "/findBySubmitterIdAndIsPublicSummary";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("submitterId", submitterId.toString());
//        params.put("isPublic", isPublic.toString());
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public ResponseEntity<String> findBySubmitterId(Long submitterId) {
//        final String url = "/findBySubmitterId/{submitterId}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("submitterId", submitterId.toString());
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findBySubmitterIdSummary(Long submitterId) {
//
//        final String url = "/findBySubmitterIdSummary/{submitterId}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("submitterId", submitterId.toString());
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findByReviewer(String user_aap_ref) {
//
//        final String url = "/findByReviewer/{user_aap_ref}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("user_aap_ref", user_aap_ref);
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findByReviewerSummary(String user_aap_ref) {
//
//        final String url = "/findByReviewerSummary/{user_aap_ref}";
//
//        // set uri parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("user_aap_ref", user_aap_ref);
//
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, params);
//        System.out.println(response.getBody());
//        return response;
//    }
//
//
//    public ResponseEntity<String> findAllAccessionsChanged() {
//        final String url = "/findAllAccessionsChanged";
//        ResponseEntity<String> response = requestClient.makeGetRequest(url, null);
//        return response;
//    }
//
//
//    public List<List<String>> findMonthlySubmissions() throws JsonProcessingException {
//        final String url = "/findMonthlySubmissions";
//        String response = restClientService.sendGetRequestWithRetry(url);
//        List list = objectMapper.readValue(response, List<List<String>>.class);
//        return response;
//    }
}

