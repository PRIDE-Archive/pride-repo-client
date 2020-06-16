package uk.ac.ebi.pride.repoclient.priderepo.service;



import com.fasterxml.jackson.databind.JsonNode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.ac.ebi.pride.archive.repo.util.ObjectMapper;

/**
 * @author Suresh Hewapathirana
 */
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void findByAccessionTest(){
        final String accession = "PXD014195";
        projectService.findByAccession(accession);
    }

    @Test
    @DisplayName("Should be equal")
    void findById() {
        final long id = 162010100;
        ResponseEntity<String> response = projectService.findById(id);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findByIdSummary() {
    }

    @Test
    void count() {
    }

    @Test
    void getAllAccessions() {
    }

    @Test
    void getAllPublicAccessions() {
    }

    @Test
    void findByAccession() {
    }

    @Test
    void findByAccessionSummary() {
    }

    @Test
    void findBySubmitterIdAndIsPublic() {
    }

    @Test
    void findBySubmitterIdAndIsPublicSummary() {
    }

    @Test
    void findBySubmitterId() {
    }

    @Test
    void findBySubmitterIdSummary() {
    }

    @Test
    void findByReviewer() {
    }

    @Test
    void findByReviewerSummary() {
    }

    @Test
    void findAllAccessionsChanged() {
    }

    @Test
    void findMonthlySubmissions() {
    }
}