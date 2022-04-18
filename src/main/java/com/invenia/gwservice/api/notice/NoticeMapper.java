package com.invenia.gwservice.api.notice;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
  List<Notice> selectAllByCriteria(Criteria criteria);
}
