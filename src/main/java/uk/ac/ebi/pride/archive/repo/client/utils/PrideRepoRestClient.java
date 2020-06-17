package uk.ac.ebi.pride.archive.repo.client.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class PrideRepoRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKeyName;
    private final String apiKeyValue;

    public PrideRepoRestClient(String baseUrl, String apiKeyName, String apiKeyValue) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
        this.apiKeyName = apiKeyName;
        this.apiKeyValue = apiKeyValue;
    }

    //TODO retry logics
    public String sendGetRequestWithRetry(String url, Map<String, String> params) {
        return makeGetRequest(url, params);
    }

    //TODO retry logics
    public String sendGetRequestWithRetry(String url) {
        return makeGetRequest(url, null);
    }

    private String makeGetRequest(String url, Map<String, String> params) {
        ResponseEntity<String> response = null;
        try {
            //  create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set(apiKeyName, apiKeyValue);

            // build the request
            HttpEntity entity = new HttpEntity(headers);

            // make an HTTP GET request with headers and parameters
            if (params == null) {
                response = restTemplate.exchange(baseUrl + url, HttpMethod.GET, entity, String.class);
            } else {
                response = restTemplate.exchange(baseUrl + url, HttpMethod.GET, entity, String.class, params);
            }
            if (response.getStatusCode() != HttpStatus.OK) {
                String s = "Received invalid response for : " + url + " : " + response;
                log.error(s);
                throw new IllegalStateException(s);
            }
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return response.getBody();
    }

}
