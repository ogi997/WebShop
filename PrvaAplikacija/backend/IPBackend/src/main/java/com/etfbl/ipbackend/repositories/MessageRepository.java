package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Optional<Message> findById(Integer id);
    List<Message> findAll();
    Message save(Message message);
    void deleteById(Integer id);
}
