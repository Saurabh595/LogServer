package com.log.project.log.server.demo.forwarder;

import com.log.project.log.server.demo.exception.RequestNotValidException;
import com.log.project.log.server.demo.forwarder.service.ForwarderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForwarderRestController {

    @Autowired
    private ForwarderService forwarderService;

    private final static Logger log = LoggerFactory.getLogger(ForwarderRestController.class);

    @RequestMapping(value="getForwarder", method = RequestMethod.GET)
    @ResponseBody
    public Forwarder getForwarder(@RequestParam String hostName) {

        if(hostName == null || hostName.isEmpty())
        {
            throw new RequestNotValidException("host name not specified");
        }
        return forwarderService.getForwarder(hostName);
    }
}
