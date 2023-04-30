package com.example.naumentest.network.http.request;

import com.example.naumentest.util.AgifyApiDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequestToAgifyApiHandler {
    private final RestTemplate restTemplate = new RestTemplate();

    public AgifyApiDTO getDataAboutMissingName(String name) {
        String url = "https://api.agify.io/";
        String requestUrl = url + "?name=" + name;
        return this.restTemplate.getForObject(requestUrl, AgifyApiDTO.class);
    }
}
