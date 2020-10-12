package com.log.project.log.server.demo.logServer.service;


import com.log.project.log.server.demo.logServer.Log;

import java.util.List;

public interface LogService {

    void addLog(String logMessage, Integer forwarderId);
    List<Log> getLogs();
    List<Log> getLogByTimeStamp(Long timeStamp);
    List<Log> getLogByTimeStampRange(Long startTimeStamp, Long endTimeStamp);

}
