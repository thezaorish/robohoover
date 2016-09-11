package com.zaorish.robohoover.model;

public class RoboHooverSolution {

	private final Point2D position;

	private final int patches;

	public RoboHooverSolution(Point2D position, int patches) {
		this.position = position;
		this.patches = patches;
	}

	public Point2D getPosition() {
		return position;
	}

	public int getPatches() {
		return patches;
	}

}
