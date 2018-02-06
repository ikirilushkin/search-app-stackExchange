package ru.kirilushkin.service;

public interface QuestionsProvider {

    String findByText(String text, int page);
}
