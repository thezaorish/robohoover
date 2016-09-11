package com.zaorish.robohoover.dao;

import com.zaorish.robohoover.model.RoboHooverResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoboHooverResponseRepository extends MongoRepository<RoboHooverResponse, String> {

	RoboHooverResponse save(RoboHooverResponse response);

}
