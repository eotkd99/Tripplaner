package Capstone.Tripplaner.ItemService.data;

import Capstone.Tripplaner.ItemService.data.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
