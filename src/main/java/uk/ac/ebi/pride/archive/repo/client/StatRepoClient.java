package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import uk.ac.ebi.pride.archive.repo.client.utils.PrideRepoRestClient;
import uk.ac.ebi.pride.archive.repo.models.stats.StatisticsSummary;

import java.io.IOException;

@Slf4j
public class StatRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String STAT_URL_PATH = "/stats";

    StatRepoClient(ObjectMapper objectMapper, PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = objectMapper;
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public StatisticsSummary getLatestStatistics() throws IOException {
        final String url = STAT_URL_PATH + "/getLatestStatistics";
        String response = prideRepoRestClient.sendGetRequestWithRetry(url, null, null);
        StatisticsSummary statisticsSummary = objectMapper.readValue(response, StatisticsSummary.class);
        return statisticsSummary;
    }
}
