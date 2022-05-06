package com.guang.yuantodo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @RequestMapping("/create")
    public String createTodo() {
        return "create todo";
    }
}
