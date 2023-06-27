package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Optional<Avatar> findById(Integer id);
    Optional<Avatar> getAvatarByFkUserAvatar(Integer id);
    List<Avatar> findAll();
    Avatar save(Avatar avatar);
    void deleteById(Integer id);
}
