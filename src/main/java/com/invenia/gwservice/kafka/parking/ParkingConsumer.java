package com.invenia.gwservice.kafka.parking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invenia.gwservice.kafka.common.Topic;
import com.invenia.gwservice.mattermost.AttachmentsItem;
import com.invenia.gwservice.mattermost.Message;
import com.invenia.gwservice.mattermost.Props;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingConsumer extends AbstractConsumerSeekAware {

  @KafkaListener(topics = "gw_parking")
  public void userListener(String topicMessage) {
    Topic<ParkingPayload> topic = new Topic<>();
    try {
      topic = new ObjectMapper().readValue(topicMessage, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }

    sendParkingControlMessage(topic.getPayload());
  }

  private void sendParkingControlMessage(ParkingPayload payload) {
    log.debug("User Payload ==> " + payload);
    Message message = Message.builder()
        .channelId("zr36ab6x63nmzb3xbybbd1rw4o")
        .props(Props.builder()
            .attachment(AttachmentsItem.builder()
                .color("#F58221")
                .title(payload.getTitle())
                .titleLink("https://hello.inveniacorp.com/cm/5/" + payload.getBoardNo() + "/o.n")
                .authorName(payload.getDeptName() + " " + payload.getUserName() + payload.getRankName())
                .footer("Hello GW")
                .footerIcon(
                    "https://slack.inveniacorp.com/files/ggp46bs39tbcmqxot85ea59cer/public?h=WPZCr633wQGFatzeKp5dB9cpCx80mxIcOSXH6xecX8M")
                .build())
            .build())
        .build();

    WebClient webClient = WebClient.builder()
        .baseUrl("https://slack.inveniacorp.com")
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create().wiretap(true)
        ))
        .build();

    String result = webClient
        .post()
        .uri("/api/v4/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .headers(header -> header.setBearerAuth("99uqgienebf5td4ztntemti4me"))
        .bodyValue(message)
        .retrieve()
        .bodyToMono(String.class)
        .block();

    log.info("Mattermost API Response ==> {}", result);
  }
}
