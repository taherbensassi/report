package com.ewd.report.repository;

import com.ewd.report.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {

}
