package com.zaorish.robohoover.dao;

import com.zaorish.robohoover.model.RoboHooverRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoboHooverRequestRepository extends MongoRepository<RoboHooverRequest, String> {

	RoboHooverRequest save(RoboHooverRequest request);

}
