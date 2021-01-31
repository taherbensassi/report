package com.ewd.report.service.implementations;


import com.ewd.report.entity.FoundItem;
import com.ewd.report.repository.CategoryRepository;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.repository.search.FoundItemSpecification;
import com.ewd.report.repository.search.SearchCriteria;
import com.ewd.report.repository.search.SearchOperation;
import com.ewd.report.service.Interfaces.SearchService;
import info.debatty.java.stringsimilarity.JaroWinkler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    private FoundItemRepository foundItemRepository;
    private CategoryRepository categoryRepository;

    public SearchServiceImpl(FoundItemRepository foundItemRepository,CategoryRepository categoryRepository) {
        this.foundItemRepository = foundItemRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<FoundItem> search(FoundItem foundItem) {
        int match = 0;
        List<FoundItem> result = new LinkedList<FoundItem>();

        // alog https://github.com/tdebatty/java-string-similarity
        JaroWinkler jw = new JaroWinkler();
        List<FoundItem> allItems =  foundItemRepository.findAll();

        for (FoundItem allItem : allItems) {

            //Name
           double diffName = jw.similarity(allItem.getName(), foundItem.getName());
           if((allItem.getName() != null) && (allItem.getName().equals(foundItem.getName()))){
               match+= 25;
           }else if(diffName> 0.8){
               match+= 10;
           }else if(foundItem.getName() == null){
               match+= 10;
           }

            //DateFound
            //double diffName = jw.similarity(allItem.getName(), foundItem.getName());
            if(allItem.getDateFound().equals(foundItem.getDateFound())){
                match+= 25;
            }


            //Address
            double diffAddress = jw.similarity(allItem.getAddress(), foundItem.getAddress());
            if(allItem.getAddress().equals(foundItem.getAddress()) &&
                allItem.getCountry().equals(foundItem.getCountry()) &&
                allItem.getCity().equals(foundItem.getCity()) &&
                allItem.getZip().equals(foundItem.getZip())
               ){
                match+= 50;
            }else if( (diffAddress >= 0.8) &&
                    allItem.getCountry().equals(foundItem.getCountry()) &&
                    allItem.getCity().equals(foundItem.getCity()) &&
                    allItem.getZip().equals(foundItem.getZip()) ) {
                match+= 30;
            }


            //Address
            double diffAddressAdditionalInformation = jw.similarity(allItem.getAddressAdditionalInformation(), foundItem.getAddressAdditionalInformation());
            if(allItem.getAddressAdditionalInformation().equals(foundItem.getAddressAdditionalInformation())){
                match+= 10;
            }else if(diffAddressAdditionalInformation> 0.8){
                match+= 5;
            }


            //Any further Information!
            double diffAdditionalInformation = jw.similarity(allItem.getAdditionalInformation(), foundItem.getAdditionalInformation());
            if(allItem.getAdditionalInformation().equals(foundItem.getAdditionalInformation())){
                match+= 10;
            }else if(diffAdditionalInformation> 0.8){
                match+= 5;
            }

            // Brand
            double diffBrand = jw.similarity(allItem.getBrand(), foundItem.getBrand());
            if(allItem.getBrand().equals(foundItem.getBrand())){
                match+= 10;
            }else if(diffBrand> 0.8){
                match+= 5;
            }

            // Color
            double diffColor = jw.similarity(allItem.getColor(), foundItem.getColor());
            if(allItem.getColor().equals(foundItem.getColor())){
                match+= 10;
            }else if(diffColor> 0.8){
                match+= 5;
            }

            System.out.println(match);

            if(match>= 80){
                result.add(allItem);
            }
            match= 0;
        }
        return result;
    }
}
