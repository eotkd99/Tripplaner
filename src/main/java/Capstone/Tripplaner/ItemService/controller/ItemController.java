package Capstone.Tripplaner.ItemService.controller;

import Capstone.Tripplaner.ItemService.data.dto.Item;
import Capstone.Tripplaner.ItemService.data.entity.ItemEntity;
import Capstone.Tripplaner.ItemService.data.entity.ItemImgEntity;
import Capstone.Tripplaner.ItemService.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/itemList")
    public String getItemList(Model model) {
        List<ItemEntity> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "item/itemList";
    }

    // 아이템 등록 페이지
    @GetMapping("/addItem")
    public String addItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/addForm";
    }

    @PostMapping("/addItem")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult itemBindingResult) {
        if (itemBindingResult.hasErrors()) return "item/addForm";
        itemService.addItem(item);
        return "redirect:/itemList";
    }

    @GetMapping("/item/{id}/edit")
    public String itemEdit(@PathVariable Long id, Model model) {
        model.addAttribute("item",itemService.getItemById(id));
        return "item/editForm";
    }

    @PostMapping("/item/{id}/edit")
    public String itemEditSave(@Validated @ModelAttribute Item item, BindingResult itemBindingResult, @PathVariable Long id) {
        if (itemBindingResult.hasErrors()) return "item/editForm";
        itemService.editItem(item, id);
        return "redirect:/item/{id}";
    }

    @GetMapping("/item/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {
        ItemEntity itemEntity = itemService.getItemById(id);

        List<ItemImgEntity> imgList = itemEntity.getImgList();

        List<String> decodedImgList = new ArrayList<>();
        for (ItemImgEntity img : imgList) {
            String base64Data = Base64.getEncoder().encodeToString(img.getImg());
            decodedImgList.add(base64Data);
        }

        model.addAttribute("item", itemEntity);
        model.addAttribute("imgList", decodedImgList);
        return "item/item";
    }

    @PostMapping("/item/{id}/del")
    public String itemDelete(@PathVariable Long id) {
        itemService.delItem(id);
        return "redirect:/itemList";
    }
}
