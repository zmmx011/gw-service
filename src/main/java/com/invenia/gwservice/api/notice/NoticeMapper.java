package com.invenia.gwservice.api.notice;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper {

  List<Notice> selectAllByCriteria(
      @Param("sortBy") String sortBy,
      @Param("offset") int offset,
      @Param("limit") int limit);
}
