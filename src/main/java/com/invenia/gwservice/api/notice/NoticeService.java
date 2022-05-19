package com.invenia.gwservice.api.notice;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

  @Resource
  private NoticeMapper noticeMapper;

  public List<Notice> selectAllByCriteria(String sortBy, int offset, int limit) {
    return noticeMapper.selectAllByCriteria(sortBy, offset, limit);
  }
}
