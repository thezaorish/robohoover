package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.Point2D;
import com.zaorish.robohoover.model.RoboHooverConfig;
import com.zaorish.robohoover.model.RoboHooverRequest;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class RoboHooverRequestToConfigTransformer {

	public RoboHooverConfig transform(RoboHooverRequest request) {
		RoboHooverConfig config = new RoboHooverConfig();
		config.setOrigin(new Point2D(0, 0));
		config.setCorner(new Point2D(request.getRoomSize().get(0) - 1, request.getRoomSize().get(1) - 1));
		config.setSource(new Point2D(request.getCoords().get(0), request.getCoords().get(1)));
		config.setInstructions(request.getInstructions());
		config.setPatches(request.getPatches().stream().map(it -> new Point2D(it.get(0), it.get(1))).collect(toList()));
		return config;
	}

}
