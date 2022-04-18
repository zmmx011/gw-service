package com.invenia.gwservice.api.notice;

import java.util.Date;
import lombok.Data;

@Data
public class Notice {

  private Integer boardno;

  private Integer boardinfono;

  private String title;

  private String username;

  private String deptname;

  private String rankname;

  private String security;

  private String important;

  private Integer state;

  private String type;

  private Date registdate;

  private Integer hit;

  private String attachyn;

  private String reservationyn;

  private Date modifydate;

  private String userid;

  private String startdate;

  private String enddate;

  private String userno;

  private String fileno;

  private String noticeyn;

  private String activeyn;

  private String tag;

  private String contentmode;

  private String entrydate;

  private String entrydept;

  private String fixview;

  private String noticestartdate;

  private String noticeenddate;

  private String noticereserveyn;

  private String loginyn;
}
