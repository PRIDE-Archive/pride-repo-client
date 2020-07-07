package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.submission.Submission;

public class SubmissionRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String SUBMISSION_URL_PATH = "/submission";

    SubmissionRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public void save(Submission submission) throws JsonProcessingException {
        final String url = SUBMISSION_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(submission);
        prideRepoRestClient.sendPostRequest(url, payload);
    }
}
