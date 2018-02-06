package ru.kirilushkin.service;

import ru.kirilushkin.domain.SearchResult;

public interface SearchService {
    SearchResult search(String text, Integer page);
}
