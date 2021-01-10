package com.ewd.report.controller;


import com.ewd.report.entity.FoundItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class SearchController {




    @GetMapping("/search")
    public List<FoundItem> search(@RequestParam(value = "search") String search) {


        return null;
    }


}
