package com.app.blog.services;

import com.app.blog.models.Post;
import com.app.blog.repositorys.PostRepository;
import com.app.blog.repositorys.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public boolean addNewPost(Post post) {
        postRepository.save(post);
        return false;
    }
}
