package com.ewd.report.category;


import com.ewd.report.entity.Category;
import com.ewd.report.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;


}
