package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;

public class PrideRepoClientFactory {

    private final PrideRepoRestClient prideRepoRestClient;
    private final ProjectRepoClient projectRepoClient;
    private final FileRepoClient fileRepoClient;
    private final AssayRepoClient assayRepoClient;

    public PrideRepoClientFactory(String apiBaseUrl, String apiKeyName, String apiKeyValue) {
        this.prideRepoRestClient = new PrideRepoRestClient(apiBaseUrl, apiKeyName, apiKeyValue);

        ObjectMapper jacksonObjectMapper = getJacksonObjectMapper();
        this.projectRepoClient = new ProjectRepoClient(jacksonObjectMapper, prideRepoRestClient);
        this.fileRepoClient = new FileRepoClient(jacksonObjectMapper, prideRepoRestClient);
        this.assayRepoClient = new AssayRepoClient(jacksonObjectMapper, prideRepoRestClient);
    }

    public ProjectRepoClient getProjectRepoClient() {
        return projectRepoClient;
    }

    public FileRepoClient getFileRepoClient() {
        return fileRepoClient;
    }

    public AssayRepoClient getAssayRepoClient() {
        return assayRepoClient;
    }

    public ObjectMapper getJacksonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return mapper;
    }
}
