package com.guang.yuantodo.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王立广
 * @since 2022-05-06
 */
@RestController
@RequestMapping("/todos")
public class TodosController {

    @RequestMapping("create")
    public String create() {
        return "new create";
    }

}

