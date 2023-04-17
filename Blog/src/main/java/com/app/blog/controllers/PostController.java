package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/blog/post/add")
    public String postAddPage(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "posts/post_add";
    }

    @PostMapping("/blog/post/add")
    public String postAdd(@ModelAttribute("post") Post post) {
        postService.addNewPost(post);
        return "redirect:/";
    }
}
