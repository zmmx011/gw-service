package com.invenia.gwservice.kafka.eventlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invenia.gwservice.kafka.common.Topic;
import com.invenia.gwservice.rabbitmq.DestinationsConfig;
import com.invenia.gwservice.rabbitmq.DestinationsConfig.DestinationInfo;
import java.time.LocalDate;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventLogConsumer extends AbstractConsumerSeekAware {

  private final RabbitTemplate rabbitTemplate;

  private final DestinationsConfig destinationsConfig;

  @KafkaListener(topics = "gw_notify")
  public void eventLogListener(String topicMessage) {
    Topic<EventLogPayload> topic = new Topic<>();
    try {
      topic = new ObjectMapper().readValue(topicMessage, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }

    EventLogPayload payload = topic.getPayload();

    if (LocalDate.ofInstant(payload.getRegDate().toInstant(), ZoneId.systemDefault()).isBefore(LocalDate.now())) {
      return;
    }

    Notify notify = Notify.builder()
        .title("notify.gw." + payload.getMenuNo())
        .content(payload.getEventTitleData1())
        .userNo(payload.getCompanyNum())
        .build();

    log.info("New Group Ware Notify Received => {}", notify);

    DestinationInfo destinationInfo = destinationsConfig
        .getTopics()
        .get("notify");

    if (destinationInfo == null) {
      log.info("{} Topic Not Found", notify);
      return;
    }

    rabbitTemplate.convertAndSend(destinationInfo.getExchange(), payload.getCompanyNum(), notify);
  }
}
