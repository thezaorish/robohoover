package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.RoboHooverResponse;
import com.zaorish.robohoover.model.RoboHooverSolution;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class RoboHooverSolutionToResponseTransformer {

	public RoboHooverResponse transform(RoboHooverSolution solution) {
		RoboHooverResponse output = new RoboHooverResponse();
		output.setCoords(asList(solution.getPosition().getX(), solution.getPosition().getY()));
		output.setPatches(solution.getPatches());
		return output;
	}

}
