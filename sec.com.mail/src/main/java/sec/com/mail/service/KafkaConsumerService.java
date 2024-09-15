package sec.com.mail.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import sec.com.mail.model.Transaction;

import java.util.List;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "PDF_GEN", groupId = "my-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(List<Transaction> transactions) {
        // Process the transactions
        System.out.println("Received transactions: " + transactions);
        // Add your logic to process these transactions
    }
}
