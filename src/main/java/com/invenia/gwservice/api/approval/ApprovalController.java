package com.invenia.gwservice.api.approval;

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
@RequestMapping("/approval")
public class ApprovalController {

  private final ApprovalService approvalService;

  @GetMapping("/{id}/approval-status")
  @PreAuthorize("(principal.getClaimAsString('preferred_username') == #id ) or hasRole('admin')")
  public ResponseEntity<ApprovalStatus> approvalStatus(@PathVariable String id) {
    return approvalService
        .findApprovalStatusById(id)
        .map(config -> ResponseEntity.ok().body(config))
        .orElse(ResponseEntity.notFound().build());
  }

}
