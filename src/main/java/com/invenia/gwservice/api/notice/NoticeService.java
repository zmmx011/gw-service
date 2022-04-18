package com.invenia.gwservice.api.notice;

import com.invenia.gwservice.api.common.Criteria;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

  @Resource
  private NoticeMapper noticeMapper;


  public int deleteByPrimaryKey(Integer boardno, Integer boardinfono) {
    return noticeMapper.deleteByPrimaryKey(boardno, boardinfono);
  }


  public int insert(Notice record) {
    return noticeMapper.insert(record);
  }


  public int insertSelective(Notice record) {
    return noticeMapper.insertSelective(record);
  }


  public Notice selectByPrimaryKey(Integer boardno, Integer boardinfono) {
    return noticeMapper.selectByPrimaryKey(boardno, boardinfono);
  }


  public int updateByPrimaryKeySelective(Notice record) {
    return noticeMapper.updateByPrimaryKeySelective(record);
  }


  public int updateByPrimaryKey(Notice record) {
    return noticeMapper.updateByPrimaryKey(record);
  }

  public List<Notice> selectAllByCriteria(Criteria criteria) {
    return noticeMapper.selectAllByCriteria(criteria);
  }


}
