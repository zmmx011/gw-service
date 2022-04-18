package com.invenia.gwservice.api.notice;

import java.util.Date;
import lombok.Data;

@Data
public class Notice {

  private Integer boardNo;

  private Integer boardInfoNo;

  private String title;

  private String userName;

  private String deptName;

  private String rankName;

  private String security;

  private String important;

  private Integer state;

  private String type;

  private Date registerDate;

  private Integer hit;

  private String attachYN;

  private String reservationYN;

  private Date modifyDate;

  private String userId;

  private String startDate;

  private String endDate;

  private String userNo;

  private String fileNo;

  private String noticeYN;

  private String activeYN;

  private String tag;

  private String contentMode;

  private String entryDate;

  private String entryDept;

  private String fixView;

  private String noticeStartDate;

  private String noticeEndDate;

  private String noticeReserveYN;

  private String loginYN;
}
