package com.ewd.report.controller;

import com.ewd.report.entity.FoundItem;
import com.ewd.report.service.Interfaces.FoundItemService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FoundItemController {

    private final FoundItemService foundItemService;

    public FoundItemController(FoundItemService foundItemService) {
        this.foundItemService = foundItemService;
    }

    @GetMapping("/items")
    public List<FoundItem> getAllFoundItems(){
        return foundItemService.getAllFoundItems();
    }

    @PostMapping("/item")
    public FoundItem postItem(@RequestBody FoundItem FoundItem){
        return foundItemService.postItem(FoundItem);
    }

    @GetMapping("/item/{id}")
    public FoundItem getItemById(@PathVariable(value = "id") Long itemId) {
        return foundItemService.getItemById(itemId);
    }

    @PutMapping("/item/{id}")
    public boolean updateItem(@Valid @RequestBody FoundItem foundItem, @PathVariable("id") Long id){
        return foundItemService.updateItem(foundItem, id);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable("id") Long id){
        foundItemService.deleteItem(id);
    }
}
