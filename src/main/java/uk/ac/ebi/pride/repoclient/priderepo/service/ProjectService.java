package uk.ac.ebi.pride.repoclient.priderepo.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.repoclient.priderepo.util.RequestHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Suresh Hewapathirana
 */
@Service
public class ProjectService {

    RequestHelper requestHelper = new RequestHelper();

    public  ResponseEntity<String> findById(Long id) {
        final String url = "/findById/{id}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        return response;
    }


    public ResponseEntity<String> findByIdSummary(Long id) {
        final String url = "/findByIdSummary/{id}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        return response;
    }


    public ResponseEntity<String> count() {
        final String url = "/count";
        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> getAllAccessions() {
    final String url = "/getAllAccessions";
        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> getAllPublicAccessions() {
    final String url = "/getAllPublicAccessions";
        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> findByAccession(String accession){

        final String url = "/project/findByAccession/{accession}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("accession", accession);

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findByAccessionSummary(String accession) {

        final String url = "/findByAccessionSummary/{accession}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("accession", accession);

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findBySubmitterIdAndIsPublic(Long submitterId, Boolean isPublic) {
    final String url = "/findBySubmitterIdAndIsPublic";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("submitterId", submitterId.toString());
        params.put("isPublic", isPublic.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> findBySubmitterIdAndIsPublicSummary(Long submitterId, Boolean isPublic) {
    final String url = "/findBySubmitterIdAndIsPublicSummary";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("submitterId", submitterId.toString());
        params.put("isPublic", isPublic.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> findBySubmitterId(Long submitterId) {
        final String url = "/findBySubmitterId/{submitterId}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("submitterId", submitterId.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findBySubmitterIdSummary(Long submitterId) {

        final String url = "/findBySubmitterIdSummary/{submitterId}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("submitterId", submitterId.toString());

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findByReviewer(String user_aap_ref) {

        final String url = "/findByReviewer/{user_aap_ref}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("user_aap_ref", user_aap_ref);

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findByReviewerSummary(String user_aap_ref) {

        final String url = "/findByReviewerSummary/{user_aap_ref}";

        // set uri parameters
        Map<String, String> params = new HashMap<>();
        params.put("user_aap_ref", user_aap_ref);

        ResponseEntity<String> response = requestHelper.makeGetRequest(url, params);
        System.out.println(response.getBody());
        return response;
    }


    public ResponseEntity<String> findAllAccessionsChanged() {
        final String url = "/findAllAccessionsChanged";
        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }


    public ResponseEntity<String> findMonthlySubmissions() {
        final String url = "/findMonthlySubmissions";
        ResponseEntity<String> response = requestHelper.makeGetRequest(url, null);
        return response;
    }
}

