package ru.otus.amezgin.restclient.massaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.restclient.dto.BookDto;

@Service
@Slf4j
public class KafkaMessageConsumer implements MessageConsumer {

    @KafkaListener(
            topics = "${library-client.kafka-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void listen(BookDto bookDto) {
        log.info("From kafka bookDto = {}", bookDto);
    }
}
