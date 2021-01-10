package com.ewd.report.repository;

import com.ewd.report.entity.Category;
import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoundItemRepository extends JpaRepository<FoundItem, Long>, JpaSpecificationExecutor<FoundItem> {

    List<FoundItem> findByUser(User user);





}
