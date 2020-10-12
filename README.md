# LogServer

Exposed rest endpoints which different forwarders can use to register themselves and add logs in persistent storage (MongoDB)

Endpoints - 
GET /getForwarder?hostName=<unique-host-name> - Forwarders should use this endpoint to register themselves with the log server before sending the logs.
Response - 
{
  "id": <forwarder id to be used in /addLog endpoint>
  "lastReadPosition": <No of characters to skip from log file>
}

POST /addLog - Forwarders can use this endpoint to add logs. 
RequestBody format - 
{
    "forwarderId":1,
    "logMessage":"message" (log message in format "TimeStamp, <log>". Timestamp format - "dd-M-yyyy HH:mm:ss"
    "lastReadPosition":0 (optional. This will be saved and can be used by forwarder to skip these many characters when forwarder reboots after crash"
}

POST /getLogs - Return last 10 logs sorted by timestamp
GET /getLogByTimeStampRange?startTimeStamp=<start-time-stamp-in-epoch>&endTimeStamp=<end-time-stamp-in-epoch> - Returns logs between start and end timeStamp.

