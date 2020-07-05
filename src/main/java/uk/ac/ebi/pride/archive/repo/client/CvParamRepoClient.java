package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.param.CvParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CvParamRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String CVPARAM_URL_PATH = "/cvparam";

    CvParamRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public CvParam findByAccession(String accession) throws IOException {
        final String url = CVPARAM_URL_PATH + "/findByAccession/{accession}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("accession", accession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        CvParam CvParam = objectMapper.readValue(response, CvParam.class);
        return CvParam;
    }

    public List<CvParam> findAll() throws IOException {
        final String url = CVPARAM_URL_PATH + "/findAll";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<CvParam> CvParams = objectMapper.readValue(response, new TypeReference<List<CvParam>>(){} );
        return CvParams;
    }

    public CvParam save(CvParam cvParam) throws JsonProcessingException {
        final String url = CVPARAM_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(cvParam);
        String response = prideRepoRestClient.sendPostRequest(url, payload);

        return objectMapper.readValue(response, CvParam.class);
    }
}
