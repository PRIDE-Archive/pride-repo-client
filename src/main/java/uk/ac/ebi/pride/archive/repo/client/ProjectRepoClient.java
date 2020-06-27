package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.file.ProjectFile;
import uk.ac.ebi.pride.archive.repo.models.project.Project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class handles all the methods related to Project
 */
@Slf4j
public class ProjectRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String PROJECT_URL_PATH = "/project";

    ProjectRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<Project> findById(Long id) throws IOException {
        final String url = PROJECT_URL_PATH + "/findById/{id}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", id.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        Project project = objectMapper.readValue(response, Project.class);
        return Optional.ofNullable(project);
    }

    public Long count() throws IOException {
        final String url = PROJECT_URL_PATH + "/count";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null,null);
        Long count = objectMapper.readValue(response, Long.class);
        return count;
    }

    public List<String> getAllAccessions() throws IOException {
        final String url = PROJECT_URL_PATH + "/getAllAccessions";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<String> accessions = objectMapper.readValue(response, List.class);
        return accessions;
    }

    public List<String> getAllPublicAccessions() throws IOException {
        final String url = PROJECT_URL_PATH + "/getAllPublicAccessions";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<String> accessions =  objectMapper.readValue(response, List.class);
        return accessions;
    }

    public Project findByAccession(String accession) throws IOException {

        final String url = PROJECT_URL_PATH + "/findByAccession/{accession}";

        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("accession", accession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        Project project = objectMapper.readValue(response, Project.class);
        return project;
    }

    public List<String> findAllAccessionsChanged() throws IOException {
        final String url = PROJECT_URL_PATH + "/findAllAccessionsChanged";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<String> accessions =  objectMapper.readValue(response, List.class);
        return accessions;
    }

    public List<List<String>> findMonthlySubmissions() throws IOException {
        final String url = PROJECT_URL_PATH + "/findMonthlySubmissions";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<List<String>> monthlySubmissions =  objectMapper.readValue(response, List.class);
        return monthlySubmissions;
    }

    public List<Project> findBySubmitterIdAndIsPublic(Long submitterId, Boolean isPublic) throws IOException {

        final String url = PROJECT_URL_PATH + "/findBySubmitterIdAndIsPublic";

        // set query parameters
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("submitterId", submitterId.toString());
        queryParams.add("isPublic", isPublic.toString());


        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, queryParams);
        List<Project> projects = objectMapper.readValue(response, List.class);
        return projects;
    }

    public List<Project> findBySubmitterId(Long submitterId) throws IOException {

        final String url = PROJECT_URL_PATH + "/findBySubmitterId/{submitterId}";

        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("submitterId", submitterId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<Project> projects = objectMapper.readValue(response, List.class);
        return projects;
    }

    public List<Project> findByReviewer(String userAapRef) throws IOException {

        final String url = PROJECT_URL_PATH + "/findByReviewer/{user_aap_ref}";

        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("user_aap_ref", userAapRef);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<Project> projects = objectMapper.readValue(response, List.class);
        return projects;
    }

    public Project save(Project project) throws JsonProcessingException {
        final String url = PROJECT_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(project);
        String response = prideRepoRestClient.sendPostRequest(url, payload);

        return objectMapper.readValue(response, Project.class);
    }
}

