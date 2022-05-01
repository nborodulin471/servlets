package ru.netology.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post).orElseThrow(NotFoundException::new);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

