package com.invenia.gwservice.api.eventlog;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EventLogService {

  @Resource
  private EventLogMapper eventLogMapper;

  public Optional<List<EventLog>> findAllUnreadEventsById(String id) {
    return Optional.of(eventLogMapper.findAllUnreadEventsById(id));
  }
}
