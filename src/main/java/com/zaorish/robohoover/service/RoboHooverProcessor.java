package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.Point2D;
import com.zaorish.robohoover.model.RoboHooverConfig;
import com.zaorish.robohoover.model.RoboHooverSolution;
import org.springframework.stereotype.Component;

@Component
public class RoboHooverProcessor {

	public RoboHooverSolution process(RoboHooverConfig config) {
		Point2D current = config.getSource();
		int cleanedPatches = 0;

		for (int i = 0; i < config.getInstructions().length(); i++) {
			char instruction = config.getInstructions().charAt(i);

			process(config, instruction, current);
			if (config.getPatches().remove(current)) {
				cleanedPatches++;
			}
		}
		return new RoboHooverSolution(current, cleanedPatches);
	}

	private void process(RoboHooverConfig config, char instruction, Point2D current) {
		switch (instruction) {
			case 'N':
				if (current.getY() + 1 <= config.getCorner().getY()) {
					current.setY(current.getY() + 1);
				}
				break;
			case 'S':
				if (current.getY() - 1 >= config.getOrigin().getY()) {
					current.setY(current.getY() - 1);
				}
				break;
			case 'W':
				if (current.getX() - 1 >= config.getOrigin().getX()) {
					current.setX(current.getX() - 1);
				}
				break;
			case 'E':
				if (current.getX() + 1 <= config.getCorner().getX()) {
					current.setX(current.getX() + 1);
				}
				break;
			default:
				break;
		}
	}

}
