package com.log.project.log.server.demo.logServer.service;

import com.log.project.log.server.demo.logServer.Log;
import com.log.project.log.server.demo.logServer.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService
{
    @Autowired
    private LogRepository logRepository;

    @Override
    public void addLog(String logMessage, Integer forwarderId) {
        logRepository.save(new Log(logMessage, forwarderId));
    }

    @Override
    public List<Log> getLogs() {
        PageRequest request = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "timeStamp"));
        return logRepository.findAll(request).getContent();
    }

    @Override
    public List<Log> getLogByTimeStamp(Long timeStamp) {
        PageRequest request = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "timeStamp"));
        return logRepository.findByTimeStamp(timeStamp, request);
    }

    @Override
    public List<Log> getLogByTimeStampRange(Long startTimeStamp, Long endTimeStamp) {
        return logRepository.findByTimeStampBetween(startTimeStamp, endTimeStamp);
    }
}
