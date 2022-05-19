package com.invenia.gwservice.api.eventlog;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventLogMapper {

  List<EventLog> findAllUnreadEventsByIdWithCriteria(@Param("companyNum") String companyNum,
      @Param("sortBy") String sortBy,
      @Param("offset") int offset,
      @Param("limit") int limit);
}
