package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final AtomicLong counterId = new AtomicLong(0L);
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return posts.values().stream()
                .filter(post -> !post.isRemoved())
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        if (post != null && !post.isRemoved()){
            return Optional.of(post);
        }
        return Optional.empty();
    }

    /**
     * @return Возвращает сохраненный пост
     * @implNote Если поста еще нет, тогда будет он будет сохранен и возвращен с порядковым id.
     * Если пост есть, тогда он будет перезаписан и возвращен с новым значением.
     * Optional.of сделан специально, чтобы получить ошибку т.к ожидается, что он всегда что-то будет возвращать
     */
    public Optional<Post> save(Post post) {
        if (post.isRemoved()){
            return Optional.empty();
        }
        if (post.getId() == 0) {
            post.setId(counterId.incrementAndGet());
        }
        posts.put(post.getId(), post);
        return Optional.of(posts.get(post.getId()));
    }

    public void removeById(long id) {
        Post post = posts.get(id);
        if(post != null){
            post.setRemoved(true);
        }
    }
}
