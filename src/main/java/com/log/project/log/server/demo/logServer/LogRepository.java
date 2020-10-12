
package com.log.project.log.server.demo.logServer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends MongoRepository<Log, String> {

	List<Log> findByTimeStamp(@Param("timeStamp") long timeStamp, Pageable pageable);

	List<Log> findByTimeStampBetween(Long startTimeStamp, Long endTimeStamp);
}
