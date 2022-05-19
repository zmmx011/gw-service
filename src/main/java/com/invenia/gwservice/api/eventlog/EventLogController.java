package com.invenia.gwservice.api.eventlog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"SpringElInspection", "ELValidationInspection"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gw-service/v1/event-log")
@Tag(name = "event", description = "이벤트 API")
public class EventLogController {

  private final EventLogService eventLogService;

  @Operation(summary = "읽지 않은 알림 가져오기", tags = {"event"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "성공", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = EventLog.class))}),
      @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
      @ApiResponse(responseCode = "401", description = "인증되지 않음", content = @Content),
      @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
      @ApiResponse(responseCode = "404", description = "요청한 데이터가 없음", content = @Content)
  })

  @GetMapping("/{id}")
  @PreAuthorize("(principal.getClaimAsString('preferred_username') == #id ) or hasRole('admin')")
  public ResponseEntity<List<EventLog>> getUnreadEvents(
      @Parameter(example = "e1264", description = "사번") @NotEmpty @Valid
      @PathVariable String id,
      @Parameter(example = "desc", description = "정렬 기준") @Pattern(regexp = "desc|asc") @Valid
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @Parameter(example = "0", description = "오프셋") @PositiveOrZero @Valid
      @RequestParam(value = "offset", required = false) int offset,
      @Parameter(example = "10", description = "결과 한도") @Positive @Valid
      @RequestParam(value = "limit", required = false) int limit) {
    return eventLogService
        .findAllUnreadEventsByIdWithCriteria(id, sortBy, offset, limit)
        .map(eventLog -> ResponseEntity.ok().body(eventLog))
        .orElse(ResponseEntity.notFound().build());
  }
}
