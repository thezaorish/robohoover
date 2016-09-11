package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.Point2D;
import com.zaorish.robohoover.model.RoboHooverResponse;
import com.zaorish.robohoover.model.RoboHooverSolution;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoboHooverSolutionToResponseTransformerTest {

	private RoboHooverSolutionToResponseTransformer transformer;

	@Before
	public void setUp() {
		transformer = new RoboHooverSolutionToResponseTransformer();
	}

	@Test
	public void shouldTransform() {
		// given
		RoboHooverSolution solution = new RoboHooverSolution(new Point2D(3, 5), 2);

		// when
		RoboHooverResponse response = transformer.transform(solution);

		// then
		assertThat(response.getPatches(), is(2));
		assertThat(response.getCoords(), is(asList(3, 5)));
	}

}