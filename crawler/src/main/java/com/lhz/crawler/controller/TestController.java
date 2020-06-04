package com.lhz.crawler.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2020/6/4 0:59
 */
@RestController
public class TestController
{
    public TestController()
    {
    }

    @GetMapping(value = {"/user/{id}","/user"})
    public String get(@PathVariable(required = false)String id){
        System.out.println(id);
        return "";
    }
}
