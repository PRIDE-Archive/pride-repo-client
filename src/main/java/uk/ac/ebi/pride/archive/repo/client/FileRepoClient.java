package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.file.ProjectFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FileRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String FILE_URL_PATH = "/files";

    FileRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<ProjectFile> findById(Long fileId) throws IOException {
        final String url = FILE_URL_PATH + "/findById/{fileId}";

        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("fileId", fileId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        ProjectFile projectFile = objectMapper.readValue(response, ProjectFile.class);
        return Optional.ofNullable(projectFile);
    }

    public List<ProjectFile> findAllByProjectId(Long projectId) throws IOException {
        final String url = FILE_URL_PATH + "/findAllByProjectId/{projectId}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("projectId", projectId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<ProjectFile> projectFiles = objectMapper.readValue(response, List.class);
        return projectFiles;
    }

    public List<ProjectFile> findAllByProjectAccession(String projectAccession) throws IOException {
        final String url = FILE_URL_PATH + "/findAllByProjectAccession/{projectAccession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("projectAccession", projectAccession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<ProjectFile> projectFiles = objectMapper.readValue(response, List.class);
        return projectFiles;
    }

    public List<ProjectFile> findAllByAssayId(Long assayId) throws IOException {
        final String url = FILE_URL_PATH + "/findAllByAssayId/{assayId}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("assayId", assayId.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<ProjectFile> projectFiles = objectMapper.readValue(response, List.class);
        return projectFiles;
    }

    public List<ProjectFile> findAllByAssayAccession(String assayAccession) throws IOException {
        final String url = FILE_URL_PATH + "/findAllByAssayAccession/{assayAccession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("assayAccession", assayAccession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        List<ProjectFile> projectFiles = objectMapper.readValue(response, List.class);
        return projectFiles;
    }
}
