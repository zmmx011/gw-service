package com.invenia.gwservice.api.approval;

import com.invenia.gwservice.api.approval.dto.ApprovalStatus;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApprovalService {

  @Resource
  private ApprovalMapper approvalMapper;

  public Optional<ApprovalStatus> findApprovalStatusById(String id) {
    return Optional.of(ApprovalStatus.builder()
        .inApprovalCount(approvalMapper.selectInApprovalCountById(id))
        .inProgressCount(approvalMapper.selectInProgressCountById(id))
        .unreadCompleteCount(approvalMapper.selectUnreadCompleteCountById(id))
        .unreadReturnCount(approvalMapper.selectUnreadReturnCountById(id))
        .build());
  }

}
