package com.ewd.report.service.implementations;


import com.ewd.report.entity.FoundItem;
import com.ewd.report.service.Interfaces.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public Map<String, String> search(FoundItem foundItem) {
        log.debug("Authenticating {}", foundItem.getAddress());

        return null;
    }
}
