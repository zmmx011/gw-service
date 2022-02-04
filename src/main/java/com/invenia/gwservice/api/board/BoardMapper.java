package com.invenia.gwservice.api.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

  int deleteByPrimaryKey(@Param("boardno") Integer boardno, @Param("boardinfono") Integer boardinfono);

  int insert(Board record);

  int insertSelective(Board record);

  Board selectByPrimaryKey(@Param("boardno") Integer boardno, @Param("boardinfono") Integer boardinfono);

  int updateByPrimaryKeySelective(Board record);

  int updateByPrimaryKey(Board record);
}
