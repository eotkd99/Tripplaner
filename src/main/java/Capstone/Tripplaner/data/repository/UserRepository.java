package Capstone.Tripplaner.data.repository;

import Capstone.Tripplaner.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PostEntity, String> {

}
