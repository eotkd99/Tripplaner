package Capstone.Tripplaner.ItemService.controller;

import Capstone.Tripplaner.ItemService.data.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ItemCountryController {
    /*화면 출력*/
    @GetMapping
    public String returnJapan(Model model){
        model.addAttribute("itemList",new Item());
        return "item/japan";
    }
    @GetMapping
    public String returnAmerica(Model model){
        model.addAttribute("itemList",new Item());
        return "item/america";
    }
    @GetMapping
    public String returnSouthWest(Model model){
        model.addAttribute("itemList",new Item());
        return "item/southwest";
    }
    /*아이템 추가*/
    @PostMapping
    public String addJapan(){

        return "redirect:/";
    }
    @PostMapping
    public String addAmerica(){

        return "redirect:/";
    }@PostMapping
    public String addSouthWest(){

        return "redirect:/";
    }
}
