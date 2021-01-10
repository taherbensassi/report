package com.ewd.report.service.Interfaces;


import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;

import java.util.List;

public interface FoundItemService {

    List<FoundItem> getAllFoundItems();

    FoundItem postItem(FoundItem foundItem);

    FoundItem getItemById(Long id);

    Boolean updateItem(FoundItem foundItem, Long id);

    void deleteItem(Long id);

    List<FoundItem> getItemByUser(User user);
}
