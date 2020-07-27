package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.accession.AccessionInputWrapper;
import uk.ac.ebi.pride.archive.repo.models.accession.PrideAccessions;
import uk.ac.ebi.pride.archive.repo.models.user.User;

public class AccessionRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String ACCESSION_PROVIDER_PATH = "/accession-provider";

    AccessionRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public PrideAccessions getAccessions(AccessionInputWrapper accessionInputWrapper) throws Exception {
        String payload = objectMapper.writeValueAsString(accessionInputWrapper);
        String response = prideRepoRestClient.sendPostRequest(ACCESSION_PROVIDER_PATH, payload);
        return objectMapper.readValue(response, PrideAccessions.class);
    }

    public User getLastAccession(String type, String mode) throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("type", type);
        requestParams.add("mode", mode);

        String response = prideRepoRestClient.sendGetRequestWithRetry(ACCESSION_PROVIDER_PATH, null, requestParams);
        return objectMapper.readValue(response, User.class);
    }
}
