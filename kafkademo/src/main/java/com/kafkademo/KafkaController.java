package com.kafkademo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {
    @Autowired
    private KafkaProducer producer;
    @PostMapping
    @ResponseBody
    public String sendMessage(@RequestParam("name") String name,
                              @RequestParam("age") int age) {
        this.producer.sendMessage(name, age);
        return "success";
    }
}
