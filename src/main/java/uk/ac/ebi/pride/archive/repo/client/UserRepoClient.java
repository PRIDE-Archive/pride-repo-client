package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.project.ProjectSummary;
import uk.ac.ebi.pride.archive.repo.models.user.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String USER_URL_PATH = "/user";

    UserRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<List<ProjectSummary>> findAllProjectsById(Long id) throws IOException {
        final String url = USER_URL_PATH + "/findAllProjectsById/{id}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", id.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        List<ProjectSummary> projectSummaries = objectMapper.readValue(response, new TypeReference<List<ProjectSummary>>() {
        });
        return Optional.ofNullable(projectSummaries);
    }

    public Optional<List<User>> findAllByProjectId(Long id) throws IOException {
        final String url = USER_URL_PATH + "/findAllByProjectId/{id}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", id.toString());

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        List<User> users = objectMapper.readValue(response, new TypeReference<List<User>>() {
        });
        return Optional.ofNullable(users);
    }

    public Optional<User> findByEmail(String email) throws IOException {
        final String url = USER_URL_PATH + "/findByEmail/{email}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("email", email);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null")) {
            return Optional.empty();
        }
        User user = (User) objectMapper.readValue(response, List.class);
        return Optional.ofNullable(user);
    }

    public User saveUser(User user) throws IOException {
        final String url = USER_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(user);
        String response = prideRepoRestClient.sendPostRequest(url, payload);

        return objectMapper.readValue(response, User.class);
    }

    public List<User> findUsersNotInAAP() throws IOException {
        final String url = USER_URL_PATH + "/findUsersNotInAAP";

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        List<User> users = objectMapper.readValue(response, new TypeReference<List<User>>() {
        });
        return users;
    }

    public User findByUserRef() throws IOException {
        final String url = USER_URL_PATH + "/findByUserRef";

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        User user = (User) objectMapper.readValue(response, List.class);
        return user;
    }

}
