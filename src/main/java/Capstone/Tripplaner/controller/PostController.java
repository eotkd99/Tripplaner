package Capstone.Tripplaner.controller;

import Capstone.Tripplaner.data.dto.Post;
import Capstone.Tripplaner.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Slf4j
@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/postList")
    public String postList(Model model) {
        List<Post> postList = postService.getAllPosts();
        model.addAttribute("postList", postList);
        return "post/postList";
    }
    @GetMapping("/post/{id}")
    public String postShow(@PathVariable Integer id, Model model) {
        model.addAttribute("post",postService.getPostById(id));
        return "post/post";
    }

    @GetMapping("/post/{id}/edit")
    public String postEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("post",postService.getPostById(id));
        return "post/editForm";
    }

    @PostMapping("/post/{id}/edit")
    public String postEditSave(@Validated @ModelAttribute Post post, BindingResult bindingResult,  @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/editForm";
        }

        postService.updatePost(id, post);
        return "redirect:/post/{id}";
    }
}