package com.app.blog.repositorys;

import com.app.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post getByTitle(String title);
}
