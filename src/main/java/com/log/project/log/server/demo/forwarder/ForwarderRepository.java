package com.log.project.log.server.demo.forwarder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ForwarderRepository extends CrudRepository<Forwarder, Integer> {

    Forwarder findByHostName(@Param("hostName") String hostName);
}
