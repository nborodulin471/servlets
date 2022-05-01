package ru.netology.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

@Configuration
public class Config {
    @Bean
    Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    PostRepository repository() {
        return new PostRepository();
    }

    @Bean
    PostService service(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    PostController controller(PostService service, Gson gson) {
        return new PostController(gson, service);
    }
}
