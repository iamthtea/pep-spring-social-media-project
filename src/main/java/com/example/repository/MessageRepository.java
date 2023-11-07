package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    // This method retrieves all messages written by a specified user.
    @Query("FROM Message WHERE posted_by = :user")
    List<Message> getMessagesByUser(@Param("user") String user);
}
