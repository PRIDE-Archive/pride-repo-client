package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.project.Project;
import uk.ac.ebi.pride.archive.repo.models.submission.SubmissionDto;

public class SubmissionRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String SUBMISSION_URL_PATH = "/submission";

    SubmissionRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public void save(SubmissionDto submissionDto) throws JsonProcessingException {
        final String url = SUBMISSION_URL_PATH + "/save";
        Project project = submissionDto.getProject();
        Long id = project.getId();
        if (id == null) {
            id = -1L;
            project.setId(id);
        }
        String payload = objectMapper.writeValueAsString(submissionDto);
        payload = payload.replaceAll("\"project\":null", "\"project\":" + id);//to avoid null constraint checks for foreign keys
        payload = payload.replaceAll("\"project\": null", "\"project\":" + id);
        prideRepoRestClient.sendPostRequest(url, payload);
    }
}
