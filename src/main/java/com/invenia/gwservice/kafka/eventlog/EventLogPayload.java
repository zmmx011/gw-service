package com.invenia.gwservice.kafka.eventlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
public class EventLogPayload {

	@JsonProperty("NO")
	private Integer no;
	@JsonProperty("USERNO")
	private String userNo;
	@JsonProperty("EVENTCODE")
	private String eventCode;
	@JsonProperty("EVENTDATA1")
	private String eventData1;
	@JsonProperty("EVENTDATA2")
	private String eventData2;
	@JsonProperty("EVENTTITLERESID")
	private String eventTitleResId;
	@JsonProperty("EVENTTITLEDATA1")
	private String eventTitleData1;
	@JsonProperty("EVENTTITLEDATA2")
	private String eventTitleData2;
	@JsonProperty("REGDATE")
	private Date regDate;
	@JsonProperty("MENUNO")
	private String menuNo;
	@JsonProperty("READYN")
	private String readYN;
	@JsonProperty("COMPANYNUM")
	private String companyNum;
}
