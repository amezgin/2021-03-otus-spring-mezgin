package ru.otus.amezgin.library.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.domain.Book;

@Service
public class KafkaMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, Book> kafkaTemplate;

    private final String topicName;

    public KafkaMessageProducer(KafkaTemplate<String, Book> kafkaTemplate, @Value(value = "${library.kafka-topic-name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void send(Book book) {
        kafkaTemplate.send(topicName, book);
    }
}
