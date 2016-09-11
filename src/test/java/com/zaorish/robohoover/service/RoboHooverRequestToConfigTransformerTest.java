package com.zaorish.robohoover.service;

import com.zaorish.robohoover.model.Point2D;
import com.zaorish.robohoover.model.RoboHooverConfig;
import com.zaorish.robohoover.model.RoboHooverRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoboHooverRequestToConfigTransformerTest {

	private RoboHooverRequestToConfigTransformer transformer;

	@Before
	public void setUp() {
		transformer = new RoboHooverRequestToConfigTransformer();
	}

	@Test
	public void shouldTransform() {
		// given
		RoboHooverRequest request = new RoboHooverRequest();
		request.setRoomSize(asList(2, 3));
		request.setCoords(asList(1, 1));
		request.setPatches(asList(asList(1, 2), asList(0, 1)));
		request.setInstructions("EWSN");

		// when
		RoboHooverConfig config = transformer.transform(request);

		// then
		assertThat(config.getOrigin(), is(new Point2D(0, 0)));
		assertThat(config.getCorner(), is(new Point2D(1, 2)));
		assertThat(config.getSource(), is(new Point2D(1, 1)));
		assertThat(config.getInstructions(), is("EWSN"));
		assertThat(config.getPatches(), is(new ArrayList<>(asList(new Point2D(1, 2), new Point2D(0, 1)))));
	}

}