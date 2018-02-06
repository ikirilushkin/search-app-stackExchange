package ru.kirilushkin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import ru.kirilushkin.deserialize.DateFromSecondsDeserializer;

import java.util.Date;

public class Question {

    @Getter
    private String title;

    private Date date;

    private boolean isAnswered;

    @Getter
    private String link;

    @Getter
    private Owner owner;

    @JsonProperty("isAnswered")
    public boolean isAnswered() {
        return isAnswered;
    }

    @JsonProperty("is_answered")
    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("creation_date")
    @JsonDeserialize(using = DateFromSecondsDeserializer.class)
    public void setDate(Date date) {
        this.date = date;
    }
}
