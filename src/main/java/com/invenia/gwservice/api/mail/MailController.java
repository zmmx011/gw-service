package com.invenia.gwservice.api.mail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
@RequestMapping("/gw-service/v1/mail")
@Tag(name = "mail", description = "메일 API")
public class MailController {

  private final MailService mailService;

  @Operation(summary = "읽지 않은 메일 개수", tags = {"mail"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "성공", content = @Content),
      @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
      @ApiResponse(responseCode = "401", description = "인증되지 않음", content = @Content),
      @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
      @ApiResponse(responseCode = "404", description = "요청한 데이터가 없음", content = @Content)
  })

  @GetMapping("/{id}/unread-count")
  @PreAuthorize("(principal.getClaimAsString('preferred_username') == #id ) or hasRole('admin')")
  public ResponseEntity<Integer> unreadCount(
      @Parameter(example = "e1264", description = "사번") @NotEmpty @Valid
      @PathVariable String id) {
    return mailService
        .findUnreadMailCountById(id)
        .map(config -> ResponseEntity.ok().body(config))
        .orElse(ResponseEntity.notFound().build());
  }

}
