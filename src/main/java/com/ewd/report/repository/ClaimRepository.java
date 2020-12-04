package com.ewd.report.repository;

import com.ewd.report.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaimRepository extends JpaRepository<Category, Long> {

}
