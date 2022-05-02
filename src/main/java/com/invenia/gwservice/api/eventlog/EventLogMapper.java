package com.invenia.gwservice.api.eventlog;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventLogMapper {

  List<EventLog> findAllUnreadEventsByIdWithCriteria(@Param("companyNum") String companyNum,
      @Param("criteria") Criteria criteria);
}
