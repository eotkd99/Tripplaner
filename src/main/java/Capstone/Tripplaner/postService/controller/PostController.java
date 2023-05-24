package Capstone.Tripplaner.postService.controller;

import Capstone.Tripplaner.loginService.data.User;
import Capstone.Tripplaner.postService.data.Post;
import Capstone.Tripplaner.postService.data.PostForm;
import Capstone.Tripplaner.postService.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (bindingResult.hasErrors()) return "post/editForm";
        postService.updatePost(id, post);
        return "redirect:/post/{id}";
    }

    @GetMapping("/post/add")
    public String postAdd(Model model) {
        model.addAttribute("post", new Post());
        return "post/addForm";
    }

    @PostMapping("/post/add")
    public String postAddShow(@Validated @ModelAttribute("post") PostForm form, BindingResult bindingResult,
                              @SessionAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/addForm";
        }
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setUserID(user.getUsername());
        Integer postId = postService.savePost(post);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/post/{postId}";
    }
}