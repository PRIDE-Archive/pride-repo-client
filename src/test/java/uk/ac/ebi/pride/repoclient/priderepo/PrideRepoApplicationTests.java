package uk.ac.ebi.pride.repoclient.priderepo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.ebi.pride.repoclient.priderepo.service.ProjectService;

@SpringBootTest
class PrideRepoApplicationTests {

    @Autowired
    ProjectService projectService;

    @Test
    void contextLoads() {
    }

    @Test
    public void findByAccessionTest(){
        final String accession = "PXD014195";
        projectService.findByAccession(accession);
    }
}
