package ru.kirilushkin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class SearchResult {

    @Getter
    @JsonProperty("items")
    private List<Question> questions;

    private boolean hasMore;

    @JsonProperty("hasMore")
    public boolean isHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
