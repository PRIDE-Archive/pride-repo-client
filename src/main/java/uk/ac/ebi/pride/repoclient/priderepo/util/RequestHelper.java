package uk.ac.ebi.pride.repoclient.priderepo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * @author Suresh Hewapathirana
 */

@PropertySource("classpath:application.properties")
public class RequestHelper {

    @Autowired
    RestTemplate restTemplate;
    String baseUrl;
    String apiKey;

    public RequestHelper(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ResponseEntity<String> makeGetRequest(String url, Map<String, String> params){
        ResponseEntity<String> response = null;
        try {
            //  create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("x-api-key", apiKey);

            // build the request
            HttpEntity entity = new HttpEntity(headers);

            // make an HTTP GET request with headers and parameters
            if(params == null) {
                response = restTemplate.exchange(baseUrl + url, HttpMethod.GET, entity, String.class);
            }else{
                response = restTemplate.exchange(baseUrl + url, HttpMethod.GET, entity, String.class, params);
            }
            System.out.println(response.getBody());

        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return response;
    }
}
