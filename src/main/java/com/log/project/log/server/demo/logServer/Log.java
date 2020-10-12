package com.log.project.log.server.demo.logServer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Document(collection = "Logs")
public class Log {

	@Id private String id;

	@Indexed(name = "timeStampIndex", direction = IndexDirection.DESCENDING)
	private Long timeStamp;
	private String logMessage;
	private Integer forwarderId;

	public Log(String logMessage, Integer forwarderId)
	{
		this.timeStamp = findTimeStamp(logMessage);
		this.logMessage = logMessage;
		this.forwarderId = forwarderId;
	}

	private Long findTimeStamp(String logMessage)
	{
		String timeStr = logMessage.split(",")[0];
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		long epoch;
		try {
			Date date = formatter.parse(timeStr);
			epoch = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			epoch = System.currentTimeMillis();
		}
		return epoch;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public Integer getForwarderId() {
		return forwarderId;
	}

	public void setForwarderId(Integer forwarderId) {
		this.forwarderId = forwarderId;
	}

	@Override
	public String toString() {
		LocalDateTime date =
				Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
		return String.format(
				"Log[id=%s, timeStamp='%s', forwarderId='%d', logMessage='%s']",
				id, date.toString(), forwarderId, logMessage);
	}
}
