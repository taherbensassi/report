package com.ewd.report.repository;

import com.ewd.report.entity.Category;
import com.ewd.report.entity.Message;
import com.ewd.report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> , CrudRepository <Message, Long>{

     Message findById(Integer id);

     @Query("SELECT  m  FROM Message m" +
             " WHERE  m.receiver = ?1")
     List<Message> getMessageReceived(User user);


}
