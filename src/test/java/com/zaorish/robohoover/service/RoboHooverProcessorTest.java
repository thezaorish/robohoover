package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.Point2D;
import com.zaorish.robohoover.model.RoboHooverConfig;
import com.zaorish.robohoover.model.RoboHooverSolution;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoboHooverProcessorTest {

	private RoboHooverProcessor processor;

	private RoboHooverConfig config;

	@Before
	public void setUp() {
		processor = new RoboHooverProcessor();
		config = new RoboHooverConfig();
	}

	@Test
	public void shouldProcessConfig() {
		// given
		config.setOrigin(new Point2D(0, 0));
		config.setCorner(new Point2D(4, 4));
		config.setSource(new Point2D(1, 2));
		config.setPatches(new ArrayList<>(asList(new Point2D(1, 0), new Point2D(2, 2), new Point2D(2, 3))));
		config.setInstructions("NNESEESWNWW");

		// when
		RoboHooverSolution solution = processor.process(config);

		// then
		assertThat(solution.getPosition(), is(new Point2D(1, 3)));
		assertThat(solution.getPatches(), is(1));
	}

	@Test
	public void robotShouldSkidInPlaceWhenWallIsMet() {
		// given
		config.setOrigin(new Point2D(0, 0));
		config.setCorner(new Point2D(3, 3));
		config.setSource(new Point2D(0, 0));
		config.setPatches(new ArrayList<>(asList(new Point2D(0, 0), new Point2D(0, 3), new Point2D(3, 0), new Point2D(3, 3))));
		config.setInstructions("NNNNNEEEEESSSSSWWWWW");

		// when
		RoboHooverSolution solution = processor.process(config);

		// then
		assertThat(solution.getPosition(), is(new Point2D(0, 0)));
		assertThat(solution.getPatches(), is(4));
	}

	@Test
	public void robotShouldCleanDirtPatchOnlyOnce() {
		// given
		config.setOrigin(new Point2D(0, 0));
		config.setCorner(new Point2D(4, 1));
		config.setSource(new Point2D(1, 1));
		config.setPatches(new ArrayList<>(asList(new Point2D(2, 0), new Point2D(2, 1))));
		config.setInstructions("EEEEWWWW");

		// when
		RoboHooverSolution solution = processor.process(config);

		// then
		assertThat(solution.getPosition(), is(new Point2D(0, 1)));
		assertThat(solution.getPatches(), is(1));
	}

	@Test
	public void shouldHandleNoDrivingInstructions() {
		// given
		config.setOrigin(new Point2D(0, 0));
		config.setCorner(new Point2D(2, 2));
		config.setSource(new Point2D(1, 1));
		config.setPatches(new ArrayList<>(asList(new Point2D(2, 0))));
		config.setInstructions("");

		// when
		RoboHooverSolution solution = processor.process(config);

		// then
		assertThat(solution.getPosition(), is(new Point2D(1, 1)));
		assertThat(solution.getPatches(), is(0));
	}

}