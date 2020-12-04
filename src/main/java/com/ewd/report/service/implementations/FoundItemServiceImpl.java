package com.ewd.report.service.implementations;

import com.ewd.report.entity.Category;
import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.service.Interfaces.FoundItemService;
import org.springframework.http.ResponseEntity;
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
        foundItem.setStatus(0);
        return foundItemRepository.save(foundItem);
    }


    @Override
    public FoundItem getItemById(Long id) {
        return foundItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    public Boolean updateItem(FoundItem foundItemDetails, Long id) {
        FoundItem foundItem = foundItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        // TODO: 04.12.20  update the found item



        foundItemRepository.save(foundItem);
        return true;
    }

    @Override
    public void deleteItem(Long id) {
        FoundItem foundItem = foundItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        foundItemRepository.delete(foundItem);
        ResponseEntity.ok().build();

    }
}
