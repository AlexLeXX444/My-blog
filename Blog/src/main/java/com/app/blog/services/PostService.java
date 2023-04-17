package com.app.blog.services;

import com.app.blog.models.Post;
import com.app.blog.repositorys.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public boolean addNewPost(Post post) {
        postRepository.save(post);
        return true;
    }
}
