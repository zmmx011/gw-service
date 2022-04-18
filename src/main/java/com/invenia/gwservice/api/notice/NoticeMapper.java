package com.invenia.gwservice.api.notice;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper {

  int deleteByPrimaryKey(@Param("boardno") Integer boardno, @Param("boardinfono") Integer boardinfono);

  int insert(Notice record);

  int insertSelective(Notice record);

  Notice selectByPrimaryKey(@Param("boardno") Integer boardno, @Param("boardinfono") Integer boardinfono);

  int updateByPrimaryKeySelective(Notice record);

  int updateByPrimaryKey(Notice record);

  List<Notice> selectAllByCriteria(Criteria criteria);
}
