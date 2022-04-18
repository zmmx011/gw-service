package com.invenia.gwservice.api.notice;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

  @Resource
  private NoticeMapper noticeMapper;

  public List<Notice> selectAllByCriteria(Criteria criteria) {
    return noticeMapper.selectAllByCriteria(criteria);
  }
}
