package com.invenia.gwservice.kafka.eventlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notify {

  private String title;
  private String content;
  private String userNo;

}
