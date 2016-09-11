package com.zaorish.robohoover.service;

import com.zaorish.robohoover.dao.RoboHooverRequestRepository;
import com.zaorish.robohoover.dao.RoboHooverResponseRepository;
import com.zaorish.robohoover.model.RoboHooverRequest;
import com.zaorish.robohoover.model.RoboHooverResponse;
import com.zaorish.robohoover.model.RoboHooverSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoboHooverService {

	private RoboHooverRequestRepository requestRepository;
	private RoboHooverResponseRepository responseRepository;
	private RoboHooverProcessor processor;
	private RoboHooverRequestToConfigTransformer requestToConfigTransformer;
	private RoboHooverSolutionToResponseTransformer solutionToResponseTransformer;

	@Autowired
	public RoboHooverService(RoboHooverRequestRepository requestRepository, RoboHooverResponseRepository responseRepository, RoboHooverProcessor processor,
							 RoboHooverRequestToConfigTransformer requestToConfigTransformer, RoboHooverSolutionToResponseTransformer solutionToResponseTransformer) {
		this.requestRepository = requestRepository;
		this.responseRepository = responseRepository;
		this.processor = processor;
		this.requestToConfigTransformer = requestToConfigTransformer;
		this.solutionToResponseTransformer = solutionToResponseTransformer;
	}

	public RoboHooverResponse process(RoboHooverRequest request) {
		requestRepository.save(request);
		RoboHooverSolution solution = processor.process(requestToConfigTransformer.transform(request));
		RoboHooverResponse response = solutionToResponseTransformer.transform(solution);
		responseRepository.save(response);
		return response;
	}

}
