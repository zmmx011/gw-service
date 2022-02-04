package com.invenia.gwservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invenia.gwservice.api.board.Board;
import com.invenia.gwservice.api.board.BoardService;
import com.invenia.gwservice.kafka.common.Topic;
import com.invenia.gwservice.kafka.parking.ParkingPayload;
import com.invenia.gwservice.mattermost.AttachmentsItem;
import com.invenia.gwservice.mattermost.Message;
import com.invenia.gwservice.mattermost.Props;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@ActiveProfiles("test")
@SpringBootTest(classes = GwServiceApplication.class, properties = {"spring.cloud.config.discovery.enabled:false"})
class GwServiceApplicationTests {

  Logger log = LoggerFactory.getLogger(GwServiceApplicationTests.class);

  @Autowired
  BoardService boardService;

  @Test
  void test() throws SQLException {
    Properties properties = new Properties();
    properties.setProperty("user", "g2");
    properties.setProperty("password", "g2");
    DriverManager.setLoginTimeout(40);
    Connection connection = DriverManager.getConnection("jdbc:Altibase://100.100.10.72:10003/mydb", properties);
  }

  @Test
  void mybatisTest() {
    Board board = boardService.selectByPrimaryKey(64819, 5);
    System.out.println(board);
  }

  @Test
  void sendParkingControlMessageTest() {
    String json = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"BOARDNO\"},{\"type\":\"string\",\"optional\":false,\"field\":\"TITLE\"},{\"type\":\"string\",\"optional\":false,\"field\":\"USERNAME\"},{\"type\":\"string\",\"optional\":false,\"field\":\"RANKNAME\"},{\"type\":\"string\",\"optional\":false,\"field\":\"DEPTNAME\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"org.apache.kafka.connect.data.Timestamp\",\"version\":1,\"field\":\"REGISTDATE\"}],\"optional\":false},\"payload\":{\"BOARDNO\":72890,\"TITLE\":\"[주차통제알림]성남본사 2월 12일~20일(9일간), 전체통제 진행\",\"USERNAME\":\"김정일\",\"RANKNAME\":\"책임\",\"DEPTNAME\":\"품질/생산CFT\",\"REGISTDATE\":1643042719375}}";
    Topic<ParkingPayload> topic = new Topic<>();
    try {
      topic = new ObjectMapper().readValue(json, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }

    // Topic 본문
    ParkingPayload payload = topic.getPayload();

    Message message = Message.builder()
        .channelId("qi7yefa7kfyr5kj8nkguejna9o")
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
