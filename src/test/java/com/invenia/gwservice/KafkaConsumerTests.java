package com.invenia.gwservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invenia.gwservice.kafka.common.Topic;
import com.invenia.gwservice.kafka.parking.ParkingPayload;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class KafkaConsumerTests {

  // Test 실행 시 환경변수 ENCRYPT_KEY=damu 가 필요합니다.
  Logger log = LoggerFactory.getLogger(KafkaConsumerTests.class);

  @Test
  void convertJsonToUserTopicTest() {
    String json = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"BOARDNO\"},{\"type\":\"string\",\"optional\":false,\"field\":\"TITLE\"},{\"type\":\"string\",\"optional\":false,\"field\":\"USERNAME\"},{\"type\":\"string\",\"optional\":false,\"field\":\"RANKNAME\"},{\"type\":\"string\",\"optional\":false,\"field\":\"DEPTNAME\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"org.apache.kafka.connect.data.Timestamp\",\"version\":1,\"field\":\"REGISTDATE\"}],\"optional\":false},\"payload\":{\"BOARDNO\":73332,\"TITLE\":\"[주차통제알림]파주2공장 3월 19일~23일(5일간), 부분통제 진행\",\"USERNAME\":\"김정일\",\"RANKNAME\":\"책임\",\"DEPTNAME\":\"품질/생산CFT\",\"REGISTDATE\":1646928871301}}";
    Topic<ParkingPayload> topic = new Topic<>();
    try {
      topic = new ObjectMapper().readValue(json, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    ParkingPayload payload = topic.getPayload();
    log.debug("User Payload ==> " + payload);

    log.info(new SimpleDateFormat("yyyy-MM-dd").format(payload.getRegisterDate()));

    if (LocalDate.ofInstant(payload.getRegisterDate().toInstant(), ZoneId.systemDefault()).isBefore(LocalDate.now())) {
      return;
    }

  }
}
