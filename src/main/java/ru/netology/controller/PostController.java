package ru.netology.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@RequiredArgsConstructor
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final Gson gson;
    private final PostService service;

    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        replyWithJson(response, data);
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        final var data = gson.toJson(service.getById(id));
        replyWithJson(response, data);
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        final var data = service.save(gson.fromJson(body, Post.class));
        replyWithJson(response, data);
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void replyWithJson(HttpServletResponse response, Object data) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.getWriter().print(gson.toJson(data));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
