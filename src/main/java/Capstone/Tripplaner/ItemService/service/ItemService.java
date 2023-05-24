package Capstone.Tripplaner.ItemService.service;


import Capstone.Tripplaner.ItemService.data.ItemRepository;
import Capstone.Tripplaner.ItemService.data.dto.Item;
import Capstone.Tripplaner.ItemService.data.entity.ItemEntity;
import Capstone.Tripplaner.ItemService.data.entity.ItemImgEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemRepository.save(duplicate(itemEntity, item));
    }

    public ItemEntity getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public void editItem(Item item, Long id) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);
        itemRepository.save(duplicate(itemEntity, item));
    }

    public void delItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemEntity duplicate(ItemEntity itemEntity, Item item){
        itemEntity.setTitle(item.getTitle());
        itemEntity.setContent(item.getContent());
        for (MultipartFile image : item.getImages()) {
            if (!image.isEmpty()) {
                try {
                    ItemImgEntity imgEntity = new ItemImgEntity();
                    imgEntity.setImg(image.getBytes());
                    itemEntity.addImage(imgEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return itemEntity;
    }
}
