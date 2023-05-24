package Capstone.Tripplaner.ItemService.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "item_img_entity")
public class ItemImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemEntity itemEntity;
}

