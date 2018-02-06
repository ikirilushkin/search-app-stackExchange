package ru.kirilushkin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.kirilushkin.domain.SearchResult;
import ru.kirilushkin.exception.SearchRequestException;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {

    private QuestionsProvider questionsProvider;

    private ObjectMapper objectMapper;

    public SearchServiceImpl(QuestionsProvider questionsProvider, ObjectMapper objectMapper) {
        this.questionsProvider = questionsProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public SearchResult search(String text, Integer page) {
        if (page == null) {
            page = 1;
        }
        String resultText = questionsProvider.findByText(text, page);
        try {
            return objectMapper.readValue(resultText, SearchResult.class);
        } catch (IOException e) {
            throw new SearchRequestException(e);
        }
    }
}
