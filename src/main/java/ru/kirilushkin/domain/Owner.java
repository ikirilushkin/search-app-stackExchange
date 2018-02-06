package ru.kirilushkin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Owner {

    private String name;

    @Getter
    private String link;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("display_name")
    public void setName(String name) {
        this.name = name;
    }
}
