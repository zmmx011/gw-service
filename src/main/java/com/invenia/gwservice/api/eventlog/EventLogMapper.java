package com.invenia.gwservice.api.eventlog;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventLogMapper {

  List<EventLog> findAllUnreadEventsById(@Param("companyNum") String companyNum);


}
