package com.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @KafkaListener(topics = "exam-topic", groupId = "adamsoft")
    public void consume(String message) throws IOException {
        log.info("Consumed message : {}", message);
        JSONObject messageObj = null;
        try {
            messageObj = new JSONObject(message);
            log.info(messageObj.getString("name"));
            log.info(messageObj.getInt("age") + "");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

