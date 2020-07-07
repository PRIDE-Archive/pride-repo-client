package uk.ac.ebi.pride.archive.repo.client;

public class PrideRepoClientFactory {

    private final PrideRepoRestClient prideRepoRestClient;
    private ProjectRepoClient projectRepoClient = null;
    private FileRepoClient fileRepoClient = null;
    private AssayRepoClient assayRepoClient = null;
    private StatRepoClient statRepoClient = null;
    private UserRepoClient userRepoClient = null;
    private UserProfileRepoClient userProfileRepoClient = null;
    private CvParamRepoClient cvParamRepoClient = null;
    private SubmissionRepoClient submissionRepoClient = null;

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

    public UserRepoClient getUserRepoClient() {
        if (userRepoClient == null) {
            this.userRepoClient = new UserRepoClient(prideRepoRestClient);
        }
        return userRepoClient;
    }

    public UserProfileRepoClient getUserProfileRepoClient() {
        if (userProfileRepoClient == null) {
            this.userProfileRepoClient = new UserProfileRepoClient(prideRepoRestClient);
        }
        return userProfileRepoClient;
    }

    public CvParamRepoClient getCvParamRepoClient() {
        if (cvParamRepoClient == null) {
            this.cvParamRepoClient = new CvParamRepoClient(prideRepoRestClient);
        }
        return cvParamRepoClient;
    }

    public SubmissionRepoClient getSubmissionRepoClient() {
        if (submissionRepoClient == null) {
            this.submissionRepoClient = new SubmissionRepoClient(prideRepoRestClient);
        }
        return submissionRepoClient;
    }

}
