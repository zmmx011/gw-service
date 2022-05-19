package com.invenia.gwservice.api.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gw-service/v1/notice")
@Tag(name = "notice", description = "공지사항 API")
public class NoticeController {

  private final NoticeService noticeService;

  @Operation(summary = "검색 조건을 통해 공지사항 가져오기", tags = {"notice"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "성공", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Notice.class))}),
      @ApiResponse(responseCode = "400", description = "검색 조건 누락", content = @Content),
      @ApiResponse(responseCode = "401", description = "인증되지 않음", content = @Content),
      @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)
  })

  @GetMapping("/")
  public ResponseEntity<List<Notice>> getNotice(
      @Parameter(example = "desc", description = "정렬 기준") @Pattern(regexp = "desc|asc") @Valid
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @Parameter(example = "0", description = "오프셋") @PositiveOrZero @Valid
      @RequestParam(value = "offset", required = false) int offset,
      @Parameter(example = "10", description = "결과 한도") @Positive @Valid
      @RequestParam(value = "limit", required = false) int limit) {
    return ResponseEntity.ok().body(noticeService.selectAllByCriteria(sortBy, offset, limit));
  }
}
