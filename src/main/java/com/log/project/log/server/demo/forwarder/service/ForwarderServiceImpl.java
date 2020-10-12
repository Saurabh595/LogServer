package com.log.project.log.server.demo.forwarder.service;

import com.log.project.log.server.demo.forwarder.Forwarder;
import com.log.project.log.server.demo.forwarder.ForwarderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ForwarderServiceImpl implements ForwarderService {

    @Autowired
    private ForwarderRepository forwarderRepository;

    private final static Logger log = LoggerFactory.getLogger(ForwarderServiceImpl.class);

    @Override
    public Forwarder getForwarder(String hostName) {
        Forwarder forwarder = forwarderRepository.findByHostName(hostName);
        if(forwarder == null)
        {
            //save the hostname in the db and assign a unique id
            return forwarderRepository.save(new Forwarder(hostName));
        }
        log.info(forwarder.toString());
        return forwarder;
    }

    @Override
    public void updateLastReadPosition(Long lastReadPosition, Integer forwarderId)
    {
        if(forwarderId != null)
        {
            CompletableFuture.runAsync(() -> {
                Optional<Forwarder> optionalForwarder = forwarderRepository.findById(forwarderId);
                if(optionalForwarder.isPresent())
                {
                    Forwarder forwarder = optionalForwarder.get();
                    forwarder.setLastReadPosition(lastReadPosition);
                    forwarderRepository.save(forwarder);
                }
            });
        }
    }
}
