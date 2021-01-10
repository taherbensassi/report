package com.ewd.report.repository;

import com.ewd.report.entity.Category;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> , CrudRepository <Category, Long>{

     Category findById(Integer id);

}
