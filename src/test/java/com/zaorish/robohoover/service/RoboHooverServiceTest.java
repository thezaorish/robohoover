package com.zaorish.robohoover.service;

import com.zaorish.robohoover.dao.RoboHooverRequestRepository;
import com.zaorish.robohoover.dao.RoboHooverResponseRepository;
import com.zaorish.robohoover.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static java.util.Arrays.asList;

@RunWith(MockitoJUnitRunner.class)
public class RoboHooverServiceTest {

	private RoboHooverService service;

	@Mock private RoboHooverRequestRepository requestRepository;
	@Mock private RoboHooverResponseRepository responseRepository;
	@Mock private RoboHooverProcessor processor;
	@Mock private RoboHooverRequestToConfigTransformer requestToConfigTransformer;
	@Mock private RoboHooverSolutionToResponseTransformer solutionToResponseTransformer;

	private RoboHooverConfig config = new RoboHooverConfig();
	private RoboHooverSolution solution = new RoboHooverSolution(new Point2D(3, 5), 2);
	private RoboHooverResponse response = new RoboHooverResponse();

	@Before
	public void setUp() {
		service = new RoboHooverService(requestRepository, responseRepository, processor, requestToConfigTransformer, solutionToResponseTransformer);
	}

	@Test
	public void shouldOrchestrateRequest() {
		// given
		RoboHooverRequest request = givenValidRequest();

		given(requestToConfigTransformer.transform(request)).willReturn(config);
		given(processor.process(config)).willReturn(solution);
		given(solutionToResponseTransformer.transform(solution)).willReturn(response);

		// when
		RoboHooverResponse result = service.process(request);

		// then
		verify(requestRepository).save(request);
		assertThat(result, is(response));
		verify(responseRepository).save(response);
	}

	private RoboHooverRequest givenValidRequest() {
		RoboHooverRequest request = new RoboHooverRequest();
		request.setRoomSize(asList(2, 3));
		request.setCoords(asList(1, 1));
		request.setPatches(asList(asList(1, 1)));
		request.setInstructions("EWSN");
		return request;
	}

}