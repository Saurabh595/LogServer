package com.log.project.log.server.demo.forwarder.service;

import com.log.project.log.server.demo.forwarder.Forwarder;
import org.springframework.web.bind.annotation.RequestParam;

public interface ForwarderService {

    Forwarder getForwarder(@RequestParam String hostName);
    void updateLastReadPosition(Long lastReadPosition, Integer forwarderId);
}
