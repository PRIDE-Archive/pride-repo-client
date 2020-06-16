package uk.ac.ebi.pride.archive.repo.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * @author Suresh Hewapathirana
 */

@Slf4j
@Service
public class RestClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pride-repo-api.base-url}")
    private String baseUrl;

    @Value("${pride-repo-api.key.name}")
    private String apiKeyName;

    @Value("${pride-repo-api.key.value}")
    private String apiKeyValue;

    @Retryable(backoff = @Backoff(multiplier = 2))
    public String sendGetRequestWithRetry(String url, Map<String, String> params) {
        return makeGetRequest(url, params);
    }

    @Retryable(backoff = @Backoff(multiplier = 2))
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
            if(response.getStatusCode() != HttpStatus.OK) {
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
