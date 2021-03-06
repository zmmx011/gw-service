package com.invenia.gwservice.api.approval;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApprovalStatus {

  private Integer inProgressCount;
  private Integer unreadReturnCount;
  private Integer unreadCompleteCount;
  private Integer inApprovalCount;

}
