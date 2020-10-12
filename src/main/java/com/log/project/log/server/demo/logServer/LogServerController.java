package com.log.project.log.server.demo.logServer;

import com.log.project.log.server.demo.exception.RequestNotValidException;
import com.log.project.log.server.demo.forwarder.service.ForwarderService;
import com.log.project.log.server.demo.logServer.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogServerController {

    @Autowired
    private LogService logService;

    @Autowired
    private ForwarderService forwarderService;

    private final static Logger log = LoggerFactory.getLogger(LogServerController.class);

    @RequestMapping(value="addLog", method = RequestMethod.POST)
    public void addLog(@RequestBody RequestBodyLog requestBodyLog) {
        if(validateRequestBody(requestBodyLog))
        {
            log.info(requestBodyLog.toString());
            logService.addLog(requestBodyLog.getLogMessage(), requestBodyLog.getForwarderId());
            forwarderService.updateLastReadPosition(requestBodyLog.getLastReadPosition(), requestBodyLog.getForwarderId());
        }
    }

    @RequestMapping(value="getLogs", method = RequestMethod.GET)
    public List<Log> getLogs() {
        return logService.getLogs();
    }

    @RequestMapping(value="getLogByTimeStamp", method = RequestMethod.GET)
    public List<Log> getLogByTimeStamp(@RequestParam Long timeStamp) {
        return logService.getLogByTimeStamp(timeStamp);
    }

    @RequestMapping(value="getLogByTimeStampRange", method = RequestMethod.GET)
    public List<Log> getLogByTimeStampRange(@RequestParam Long startTimeStamp, @RequestParam Long endTimeStamp) {
        return logService.getLogByTimeStampRange(startTimeStamp, endTimeStamp);
    }

    private boolean validateRequestBody(RequestBodyLog requestBodyLog)
    {
        if(requestBodyLog == null)
        {
            throw new RequestNotValidException("No request body present");
        }
        if(requestBodyLog.getLogMessage() == null)
        {
            throw new RequestNotValidException("Log Message is missing");
        }
        if(requestBodyLog.getForwarderId() == null)
        {
            throw new RequestNotValidException("Forwarder id is missing");
        }
        return true;
    }
}