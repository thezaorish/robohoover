package com.zaorish.robohoover.web;

import com.zaorish.robohoover.model.RoboHooverRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class RoboHooverRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass == RoboHooverRequest.class;
	}

	@Override
	public void validate(Object o, Errors errors) {
		RoboHooverRequest request = (RoboHooverRequest) o;
		if (request.getRoomSize() == null || request.getRoomSize().size() != 2) {
			errors.rejectValue("roomSize", "Room dimensions are mandatory, and needs to be in the form on [X, Y] coordinates!");
		}

		if (request.getCoords() == null || request.getCoords().size() != 2) {
			errors.rejectValue("coords", "Initial hover position is mandatory, and needs to be in the form on [X, Y] coordinates!");
		}

		long count = request.getPatches().stream().filter(it -> it.size() != 2).count();
		if (count != 0) {
			errors.rejectValue("patches", "All the patches need to be in the form on [X, Y] coordinates!");
		}

		if (request.getInstructions() != null && !Pattern.matches("[NSEW]+", request.getInstructions())) {
			errors.rejectValue("instructions", "Instruction need to match cardinal directions N, S, W or E!");
		}
	}

}
