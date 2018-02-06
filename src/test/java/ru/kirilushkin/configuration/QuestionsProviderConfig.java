package ru.kirilushkin.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import ru.kirilushkin.service.QuestionsProvider;
import ru.kirilushkin.service.SearchService;
import ru.kirilushkin.service.SearchServiceImpl;

@Configuration
public class QuestionsProviderConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public SearchService searchService() {
        return new SearchServiceImpl(questionsProvider(), objectMapper());
    }

    @Bean
    @Primary
    public QuestionsProvider questionsProvider() {
        return Mockito.mock(QuestionsProvider.class);
    }
}
