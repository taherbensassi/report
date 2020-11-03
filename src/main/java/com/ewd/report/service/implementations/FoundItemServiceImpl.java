package com.ewd.report.service.implementations;

import com.ewd.report.entity.FoundItem;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.service.Interfaces.FoundItemService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoundItemServiceImpl implements FoundItemService {

    private FoundItemRepository foundItemRepository;

    public FoundItemServiceImpl(FoundItemRepository foundItemRepository) {
        this.foundItemRepository = foundItemRepository;
    }

    public List<FoundItem> getAllFoundItems() {
        return foundItemRepository.findAll();
    }

    @Override
    public FoundItem postItem(FoundItem foundItem) {
           return foundItemRepository.save(foundItem);
    }

    @Override
    public FoundItem getItemById(Long id) {
        return null;
    }

    @Override
    public Boolean updateItem(FoundItem foundItem, Long id) {
        return null;
    }

    @Override
    public void deleteItem(Long id) {

    }
}
