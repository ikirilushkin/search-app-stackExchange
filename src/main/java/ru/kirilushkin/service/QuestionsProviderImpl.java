package ru.kirilushkin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kirilushkin.exception.SearchRequestException;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuestionsProviderImpl implements QuestionsProvider {

    private final RestTemplate restTemplate;

    private static final String URI_TEMPLATE_STRING = "http://api.stackexchange.com/2.2/search?page={page}&order={order}&sort={sort}&intitle={intitle}&site={site}";

    public QuestionsProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String findByText(String text, int page) {
        Map<String, Object> params = setRequestParams(text, page);
        try {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(URI_TEMPLATE_STRING, String.class, params);
            return response.getBody();
        } catch (RuntimeException e) {
            throw new SearchRequestException(e);
        }
    }

    private Map<String, Object> setRequestParams(String text, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("order", "desc");
        params.put("sort", "activity");
        params.put("site", "stackoverflow");
        params.put("intitle", text);
        params.put("page", page);
        return params;
    }
}
