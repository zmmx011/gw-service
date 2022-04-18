package com.invenia.gwservice.api.common;

import lombok.Data;

@Data
public class Criteria {

  private String sortBy;
  private int limit;
  private int offset;

}
