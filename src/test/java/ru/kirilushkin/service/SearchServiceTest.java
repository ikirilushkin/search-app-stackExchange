package ru.kirilushkin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.kirilushkin.configuration.QuestionsProviderConfig;
import ru.kirilushkin.domain.SearchResult;
import ru.kirilushkin.exception.SearchRequestException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(QuestionsProviderConfig.class)
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    private QuestionsProvider questionsProvider;

    @Test
    public void shouldReturnResult() {
        Mockito.when(questionsProvider.findByText(Data.SEARCH_TEXT, Data.PAGE)).thenReturn(getMockQuestions());
        SearchResult result = searchService.search(Data.SEARCH_TEXT, Data.PAGE);
        Assertions.assertNotNull(result);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(Data.QUESTION_COUNT, result.getQuestions().size()),
                () -> assertEquals(Data.QUESTION_TITLE, result.getQuestions().get(0).getTitle())
        );
    }

    @Test void shouldThrowException() throws RuntimeException {
        Mockito.when(questionsProvider.findByText(Data.SEARCH_TEXT, 1)).thenThrow(new SearchRequestException("Getting questions exception"));
        Assertions.assertThrows(SearchRequestException.class, () -> searchService.search(Data.SEARCH_TEXT, Data.PAGE));
    }

    private String getMockQuestions() {
        Path path = Paths.get("data/result.json");
        Stream<String> lines;
        try {
            lines = Files.lines(path);
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Data {
        public static final String SEARCH_TEXT = "java";
        public static final int PAGE = 1;
        public static final int QUESTION_COUNT = 30;
        public static final String QUESTION_TITLE = "Java Selenium how to select child elements if they exist from multiple same parent elements";
    }
}
