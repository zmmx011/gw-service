package com.invenia.gwservice.api.board;

import java.util.Date;
import lombok.Data;

@Data
public class Board {

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

  private String cacdate;

  private String activeyn;

  private Date senddate;

  private String contentmode;

  private String tag;

  private Integer agreecnt;

  private Integer opposecnt;

  private String link;

  private String linkcontent;

  private String entrydate;

  private String fixview;

  private String noticestartdate;

  private String noticeenddate;

  private String noticereserveyn;
}
