package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.user.Credentials;
import uk.ac.ebi.pride.archive.repo.models.user.User;
import uk.ac.ebi.pride.archive.repo.models.user.UserProfile;
import uk.ac.ebi.pride.archive.repo.models.user.UserSummary;

public class UserProfileRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String USER_PROFILE_URL_PATH = "/user/profile";

    UserProfileRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public String getAAPToken(Credentials credentials) throws Exception {
        final String url = USER_PROFILE_URL_PATH + "/getAAPToken";

        String payload = objectMapper.writeValueAsString(credentials);
        return prideRepoRestClient.sendPostRequest(url, payload);
    }

    public User register(UserSummary userSummary) throws Exception {
        final String url = USER_PROFILE_URL_PATH + "/register";
        String payload = objectMapper.writeValueAsString(userSummary);
        String response = prideRepoRestClient.sendPostRequest(url, payload);
        return objectMapper.readValue(response, User.class);
    }

    public UserSummary viewProfile(String jwtToken) throws Exception {
        final String url = USER_PROFILE_URL_PATH + "/view-profile";
        String response = prideRepoRestClient.sendPostRequestWithJwtAuthorization(url, null, jwtToken);
        return objectMapper.readValue(response, UserSummary.class);
    }

    public Boolean updateProfile(UserProfile userProfile, String jwtToken) throws Exception {
        final String url = USER_PROFILE_URL_PATH + "/update-profile";
        String payload = objectMapper.writeValueAsString(userProfile);
        String response = prideRepoRestClient.sendPostRequestWithJwtAuthorization(url, payload, jwtToken);
        return objectMapper.readValue(response, Boolean.class);
    }

}
