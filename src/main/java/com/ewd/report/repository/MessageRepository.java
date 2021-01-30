package com.ewd.report.repository;

import com.ewd.report.entity.Category;
import com.ewd.report.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> , CrudRepository <Message, Long>{

     Message findById(Integer id);

}
