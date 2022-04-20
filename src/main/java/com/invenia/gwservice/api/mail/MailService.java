package com.invenia.gwservice.api.mail;

import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  @Resource
  private MailMapper mailMapper;

  public Optional<Integer> findUnreadMailCountById(String id) {
    return Optional.ofNullable(mailMapper.selectUnreadMailCountById(id));
  }

}

