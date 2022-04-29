package com.invenia.gwservice.api.eventlog;

import java.util.Date;
import lombok.Data;

@Data
public class EventLog {

    private Integer no;
    private String userNo;
    private String eventCode;
    private String eventData1;
    private String eventData2;
    private String eventTitleResId;
    private String eventTitleData1;
    private String eventTitleData2;
    private Date regDate;
    private String menuNo;
    private String readYN;
    private String companyNum;
}
