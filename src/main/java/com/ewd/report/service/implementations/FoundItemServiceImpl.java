package com.ewd.report.service.implementations;

import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.CategoryRepository;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.service.Interfaces.FoundItemService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoundItemServiceImpl implements FoundItemService {

    private FoundItemRepository foundItemRepository;
    private CategoryRepository categoryRepository;

    public FoundItemServiceImpl(FoundItemRepository foundItemRepository,CategoryRepository categoryRepository) {
        this.foundItemRepository = foundItemRepository;
        this.categoryRepository = categoryRepository;
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

        // Update
        foundItem.setName(foundItemDetails.getName());
        foundItem.setAddress(foundItemDetails.getAddress());
        foundItem.setAdditionalInformation(foundItemDetails.getAdditionalInformation());
        foundItem.setAddressAdditionalInformation(foundItemDetails.getAddressAdditionalInformation());
        foundItem.setCity(foundItemDetails.getCity());
        foundItem.setBrand(foundItemDetails.getBrand());
        foundItem.setColor(foundItemDetails.getColor());
        foundItem.setCategory(foundItemDetails.getCategory());
        foundItem.setUser(foundItemDetails.getUser());
        foundItem.setImage(foundItemDetails.getImage());
        foundItem.setTimeFound(foundItemDetails.getTimeFound());
        foundItem.setDateFound(foundItemDetails.getDateFound());
        foundItem.setZip(foundItemDetails.getZip());


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

    @SneakyThrows
    @Override
    public List<FoundItem> getItemByUser(User user) {

        List<FoundItem> foundItems = foundItemRepository.findByUser(user);
        if(foundItems.isEmpty()){
            throw new NotFoundException("No Items was Found");
        }
        return  foundItems;
    }

    @Override
    public Boolean confirmReturn(FoundItem foundItemDetails, Long id) {
        FoundItem foundItem = foundItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Found-item", "id", id));
        foundItem.setReturned(foundItemDetails.getReturned());
        foundItemRepository.save(foundItem);
        return true;
    }



}
