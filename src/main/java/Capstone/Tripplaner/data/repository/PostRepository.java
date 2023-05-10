package Capstone.Tripplaner.data.repository;

import Capstone.Tripplaner.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    Optional<PostEntity> getPostById(Integer id);
}