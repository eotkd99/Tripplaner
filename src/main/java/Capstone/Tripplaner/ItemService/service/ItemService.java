package Capstone.Tripplaner.ItemService.service;


import Capstone.Tripplaner.ItemService.data.ItemRepository;
import Capstone.Tripplaner.ItemService.data.dto.Item;
import Capstone.Tripplaner.ItemService.data.dto.ItemOneImage;
import Capstone.Tripplaner.ItemService.data.entity.ItemEntity;
import Capstone.Tripplaner.ItemService.data.entity.ItemImgEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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
        itemEntity.setPrice(item.getPrice());
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

    public List<ItemEntity> descLikeSort(){
        List<ItemEntity> entities = getAllItems();
        Collections.sort(entities, Comparator.comparing(ItemEntity::getLikes).reversed());
        return entities;
    }
    public List<ItemEntity> descViewSort(){
        List<ItemEntity> entities = getAllItems();
        Collections.sort(entities, Comparator.comparing(ItemEntity::getViews).reversed());
        return entities;
    }
    public List<String> ImgProcess(List<ItemImgEntity> imgList){
        List<String> decodedImgList = new ArrayList<>();
        for (ItemImgEntity img : imgList) {
            String base64Data = Base64.getEncoder().encodeToString(img.getImg());
            decodedImgList.add(base64Data);
        }
        return decodedImgList;
    }
    public List<ItemOneImage> processItemImgList(List<ItemEntity> itemList) {
        List<ItemOneImage> itemOneImages = new ArrayList<>();
        for (ItemEntity entity : itemList) {
            itemOneImages.add(new ItemOneImage(
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getPrice(),
                    Base64.getEncoder().encodeToString(entity.getImgList().get(0).getImg())
            ));
        }
        return itemOneImages;
    }
}
