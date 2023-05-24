package Capstone.Tripplaner.postService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    Optional<PostEntity> getPostById(Integer id);
}
