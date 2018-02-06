package ru.kirilushkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirilushkin.domain.SearchResult;
import ru.kirilushkin.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public String search() {
        return "search";
    }

    @GetMapping(params = "text")
    public String search(@RequestParam String text,
                         @RequestParam(value = "page", required = false) Integer page,
                         Model model) {
        SearchResult result = searchService.search(text, page);
        model.addAttribute("search", text);
        model.addAttribute("result", result);
        model.addAttribute("page", page != null ? page : 1);
        return "search";
    }

    @GetMapping(value = "/api", params = "text")
    public @ResponseBody SearchResult search(
            @RequestParam String text,
            @RequestParam(value = "page", required = false) Integer page) {
        return searchService.search(text, page);
    }
}
