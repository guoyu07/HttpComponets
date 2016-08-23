package com.example.controller;

import com.example.utils.RedisUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lql on 2016/8/22.
 */
@RestController
@RequestMapping("/testRedis")
public class TestController {
    private Gson gson = new Gson();
    private String key = "name";
    private String value = "lql";
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/get")
    public String testSet() {
        return redisUtil.get(key);
    }

    @RequestMapping("/setKeys")
    public String setKeys() {
        Map<String, Object> values = new HashMap<>();
        values.put("name", "lql");
        values.put("age", 12);
        values.put("sex", "boy");

        String value = gson.toJson(values);
        return value;


    }
}
