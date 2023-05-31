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

    @GetMapping("/itemJapan")
    public String japanItem(Model model){
        model.addAttribute("itemList",new Item());
        return "main/japan";
    }
    /*@GetMapping
    public String japanAmerica(Model model){
        model.addAttribute("itemList",new Item());
        return "ItemCountry/america";
    }
    @GetMapping
    public String japanSouthwest(Model model){
        model.addAttribute("itemList",new Item());
        return "ItemCountry/southwest";
    }*/

    /*@PostMapping
    public String addJapan(){

        return "redirect:/";
    }
    @PostMapping
    public String addAmerica(){

        return "redirect:/";
    }@PostMapping
    public String addSouthWest(){

        return "redirect:/";
    }*/
}
