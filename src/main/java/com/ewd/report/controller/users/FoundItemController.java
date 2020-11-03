package com.ewd.report.controller.users;

import com.ewd.report.entity.FoundItem;
import com.ewd.report.service.Interfaces.FoundItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
