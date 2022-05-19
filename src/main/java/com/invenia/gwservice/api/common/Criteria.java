package com.invenia.gwservice.api.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Criteria {

  @Schema(example = "desc", description = "정렬 기준", required = true)
  private String sortBy;
  @Schema(example = "10", description = "검색 한도", required = true)
  private int limit;
  @Schema(example = "0", description = "검색 시작 위치", required = true)
  private int offset;

}
