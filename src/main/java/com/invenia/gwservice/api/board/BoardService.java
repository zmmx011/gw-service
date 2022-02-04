package com.invenia.gwservice.api.board;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Resource
    private BoardMapper boardMapper;


    public int deleteByPrimaryKey(Integer boardno, Integer boardinfono) {
        return boardMapper.deleteByPrimaryKey(boardno, boardinfono);
    }


    public int insert(Board record) {
        return boardMapper.insert(record);
    }


    public int insertSelective(Board record) {
        return boardMapper.insertSelective(record);
    }


    public Board selectByPrimaryKey(Integer boardno, Integer boardinfono) {
        return boardMapper.selectByPrimaryKey(boardno, boardinfono);
    }


    public int updateByPrimaryKeySelective(Board record) {
        return boardMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Board record) {
        return boardMapper.updateByPrimaryKey(record);
    }

}
