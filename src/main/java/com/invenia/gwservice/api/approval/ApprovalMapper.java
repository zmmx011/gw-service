package com.invenia.gwservice.api.approval;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApprovalMapper {

  Integer selectInProgressCountById(@Param("id") String id);

  Integer selectUnreadReturnCountById(@Param("id") String id);

  Integer selectUnreadCompleteCountById(@Param("id") String id);

  Integer selectInApprovalCountById(@Param("id") String id);
}
