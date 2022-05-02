package com.invenia.gwservice.api.eventlog;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"SpringElInspection", "ELValidationInspection"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event-log")
public class EventLogController {

  private final EventLogService eventLogService;

  @GetMapping("/{id}")
  @PreAuthorize("(principal.getClaimAsString('preferred_username') == #id ) or hasRole('admin')")
  public ResponseEntity<List<EventLog>> getUnreadEvents(@PathVariable String id, Criteria criteria) {
    return eventLogService
        .findAllUnreadEventsByIdWithCriteria(id, criteria)
        .map(eventLog -> ResponseEntity.ok().body(eventLog))
        .orElse(ResponseEntity.notFound().build());
  }
}
