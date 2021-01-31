package com.ewd.report.service.Interfaces;


import com.ewd.report.entity.Category;
import com.ewd.report.entity.FoundItem;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<FoundItem> search(FoundItem foundItem);

}
