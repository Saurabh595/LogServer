package com.log.project.log.server.demo.logServer;

public class RequestBodyLog
{
    private String logMessage;
    private Integer forwarderId;
    private Long lastReadPosition;

    public Integer getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(Integer forwarderId) {
        this.forwarderId = forwarderId;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Long getLastReadPosition() {
        return lastReadPosition;
    }

    public void setLastReadPosition(Long lastReadPosition) {
        this.lastReadPosition = lastReadPosition;
    }

    @Override
    public String toString() {
        return "RequestBodyLog{" +
                "logMessage='" + logMessage + '\'' +
                ", forwarderId=" + forwarderId +
                ", lastReadPosition=" + lastReadPosition +
                '}';
    }
}
