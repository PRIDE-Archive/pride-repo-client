package uk.ac.ebi.pride.archive.repo.client;

import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;

public class PrideRepoClientFactory {

    private final PrideRepoRestClient prideRepoRestClient;
    private ProjectRepoClient projectRepoClient = null;
    private FileRepoClient fileRepoClient = null;
    private AssayRepoClient assayRepoClient = null;
    private StatRepoClient statRepoClient = null;

    public PrideRepoClientFactory(String apiBaseUrl, String apiKeyName, String apiKeyValue) {
        this.prideRepoRestClient = new PrideRepoRestClient(apiBaseUrl, apiKeyName, apiKeyValue);
    }

    public ProjectRepoClient getProjectRepoClient() {
        if (projectRepoClient == null) {
            this.projectRepoClient = new ProjectRepoClient(prideRepoRestClient);
        }
        return projectRepoClient;
    }

    public FileRepoClient getFileRepoClient() {
        if (fileRepoClient == null) {
            this.fileRepoClient = new FileRepoClient(prideRepoRestClient);
        }
        return fileRepoClient;
    }

    public AssayRepoClient getAssayRepoClient() {
        if (assayRepoClient == null) {
            this.assayRepoClient = new AssayRepoClient(prideRepoRestClient);
        }
        return assayRepoClient;
    }

    public StatRepoClient getStatRepoClient() {
        if (statRepoClient == null) {
            this.statRepoClient = new StatRepoClient(prideRepoRestClient);
        }
        return statRepoClient;
    }


}
