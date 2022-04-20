package com.invenia.gwservice.api.mail;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MailMapper {

  Integer selectUnreadMailCountById(@Param("id") String id);

}
