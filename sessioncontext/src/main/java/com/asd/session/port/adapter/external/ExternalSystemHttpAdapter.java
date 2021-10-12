package main.java.com.asd.session.port.adapter.external;


import com.fasterxml.jackson.databind.util.JSONPObject;
import main.java.com.asd.session.domain.model.reservation.SpaceId;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ExternalSystemHttpAdapter {
    private static final String BASE_URI = "localhost:8080";
    private static final ExternalSystemHttpAdapter instance = new ExternalSystemHttpAdapter();

    public static ExternalSystemHttpAdapter instance() {
        return instance;
    }

    public ExternalSystemHttpAdapter() {

    }

    public UUID makeReservation(SpaceId spaceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        final String reservationUri = BASE_URI + String.format("/space/%s/reserve", spaceId.getId());
        final String jsonBody = String.format("{startDateTime:%s, endDateTime:%s}", startDateTime, endDateTime);

        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<HashMap<String, String>> responseType = new ParameterizedTypeReference<>() {};
        RequestEntity<Void> request = RequestEntity.post(reservationUri, jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        Map<String, String> jsonResponse = restTemplate.exchange(request, responseType).getBody();

        return UUID.fromString(jsonResponse.get("reservationId"));
    }
}
