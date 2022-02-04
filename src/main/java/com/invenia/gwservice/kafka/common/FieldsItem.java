package com.invenia.gwservice.kafka.common;

import lombok.Data;

@Data
public class FieldsItem {

  private String field;
  private String name;
  private Boolean optional;
  private String type;
  private Integer version;
}
