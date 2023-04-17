package com.app.blog.controllers;

import com.app.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/blog/post/add")
    public String postAddPage() {
        return "post_add";
    }
}
