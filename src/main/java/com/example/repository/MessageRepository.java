package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    // This method adds a message from a signed in user to the database.
    @Query("INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (:poster, :text, :timestamp)")
    Message creatMessage(
        @Param("poster") String poster, 
        @Param("text") String text, 
        @Param("timestamp") Long timestamp
    );

    // This method retrieves all messages from the database
    @Query("FROM Message")
    List<Message> getAllMessages();

    // This method retrieves one message based on the message id
    @Query("FROM Message WHERE message_id = :messageId")
    Message getMessageById(@Param("messageId") Integer id);

    // This method deletes a message from the database based on the message id
    @Query("DELETE FROM Message WHERE message_id = :messageId")
    void deleteMessage(@Param("messageId") Integer id);

    // This method updates the message text of a message in the database based on the message id
    @Query("UPDATE Message SET message_text = :newText WHERE message_id = :messageId")
    Message updatMessage(@Param("newText") String text, @Param("messageId") Integer id);

    // This method retrieves all messages written by a specified user.
    @Query("FROM Message WHERE posted_by = :poster")
    List<Message> getMessagesByUser(@Param("poster") String poster);
}
