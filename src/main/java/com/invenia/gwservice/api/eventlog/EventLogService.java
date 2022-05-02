package com.invenia.gwservice.api.eventlog;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EventLogService {

  @Resource
  private EventLogMapper eventLogMapper;

  public Optional<List<EventLog>> findAllUnreadEventsByIdWithCriteria(String id, Criteria criteria) {
    return Optional.of(eventLogMapper.findAllUnreadEventsByIdWithCriteria(id, criteria));
  }
}
