package com.ewd.report.controller;


import com.ewd.report.entity.FoundItem;
import com.ewd.report.service.Interfaces.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(SearchController.class);


    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Map<String, String> search(@RequestBody FoundItem foundItem) {
        return  searchService.search(foundItem);
    }
}
