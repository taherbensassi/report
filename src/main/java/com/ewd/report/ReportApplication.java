package com.ewd.report;

import com.ewd.report.entity.FoundItem;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.repository.search.FoundItemSpecification;
import com.ewd.report.repository.search.SearchCriteria;
import com.ewd.report.repository.search.SearchOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication. run(ReportApplication.class, args);
    }


    @Bean
    public CommandLineRunner specificationsDemo(FoundItemRepository foundItemRepository) {
        return args -> {
            FoundItemSpecification test = new FoundItemSpecification();
            test.add(new SearchCriteria("name", "iphone", SearchOperation.EQUAL));
            List<FoundItem> testList = foundItemRepository.findAll(test);
            testList.forEach(System.out::println);
            System.out.println("Test");
            System.out.println(Arrays.toString(testList.toArray()));
            System.out.println("Address:" +testList);
        };
    }


}
