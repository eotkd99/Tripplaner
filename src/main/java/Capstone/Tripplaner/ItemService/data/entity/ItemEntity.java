package Capstone.Tripplaner.ItemService.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImgEntity> imgList = new ArrayList<>();

    public void addImage(ItemImgEntity imgEntity) {
        imgList.add(imgEntity);
        imgEntity.setItemEntity(this);
    }

    public void removeImage(ItemImgEntity imgEntity) {
        imgList.remove(imgEntity);
        imgEntity.setItemEntity(null);
    }
}
