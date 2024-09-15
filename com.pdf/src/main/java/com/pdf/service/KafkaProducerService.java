package com.pdf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC_NAME = "PDF_GEN";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String key, Object message) {
        kafkaTemplate.send(TOPIC_NAME, key, message);
    }
}
