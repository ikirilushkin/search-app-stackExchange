package ru.kirilushkin.exception;

public class SearchRequestException extends RuntimeException {

    public SearchRequestException() {
    }

    public SearchRequestException(String message) {
        super(message);
    }

    public SearchRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchRequestException(Throwable cause) {
        super(cause);
    }

    public SearchRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
