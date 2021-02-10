package uk.ac.ebi.pride.archive.repo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.pride.archive.repo.client.utils.Utils;
import uk.ac.ebi.pride.archive.repo.models.ticket.Ticket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketRepoClient {

    private final ObjectMapper objectMapper;
    private final PrideRepoRestClient prideRepoRestClient;

    private static final String TICKET_URL_PATH = "/ticket";

    TicketRepoClient(PrideRepoRestClient prideRepoRestClient) {
        this.objectMapper = Utils.getJacksonObjectMapper();
        this.prideRepoRestClient = prideRepoRestClient;
    }

    public Optional<Ticket> findByTicketId(String ticketId) throws IOException {
        final String url = TICKET_URL_PATH + "/findByTicketId/{ticketId}";
        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("ticketId", ticketId);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null") || response.trim().isEmpty()) {
            return Optional.empty();
        }
        Ticket ticket = objectMapper.readValue(response, Ticket.class);
        return Optional.ofNullable(ticket);
    }


    public Ticket findByAccession(String accession) throws IOException {

        final String url = TICKET_URL_PATH + "/findByAccession/{accession}";

        // set uri parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("accession", accession);

        String response = prideRepoRestClient.sendGetRequestWithRetry(url, uriParams, null);
        if (response == null || response.equalsIgnoreCase("null") || response.trim().isEmpty()) {
            return null;
        }
        Ticket ticket = objectMapper.readValue(response, Ticket.class);
        return ticket;
    }

    public Ticket save(Ticket ticket) throws JsonProcessingException {
        final String url = TICKET_URL_PATH + "/save";

        String payload = objectMapper.writeValueAsString(ticket);

        String response = prideRepoRestClient.sendPostRequest(url, payload);

        return objectMapper.readValue(response, Ticket.class);
    }
}
