package com.invenia.gwservice.kafka.parking;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
public class ParkingPayload {

	@JsonProperty("REGISTDATE")
	private Date registerDate;
	@JsonProperty("BOARDNO")
	private int boardNo;
	@JsonProperty("RANKNAME")
	private String rankName;
	@JsonProperty("DEPTNAME")
	private String deptName;
	@JsonProperty("USERNAME")
	private String userName;
	@JsonProperty("TITLE")
	private String title;
}
