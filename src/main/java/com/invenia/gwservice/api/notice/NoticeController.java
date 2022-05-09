package com.invenia.gwservice.api.notice;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gw-service/v1/notice")
public class NoticeController {

  private final NoticeService noticeService;

  @GetMapping("/")
  public ResponseEntity<List<Notice>> get(Criteria criteria) {
    return ResponseEntity.ok().body(noticeService.selectAllByCriteria(criteria));
  }
}
