package com.zaorish.robohoover.web;

import com.zaorish.robohoover.model.RoboHooverRequest;
import com.zaorish.robohoover.model.RoboHooverResponse;
import com.zaorish.robohoover.service.RoboHooverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructions")
public class RoboHooverController {

	private RoboHooverService roboHooverService;

	@Autowired
	public RoboHooverController(RoboHooverService roboHooverService) {
		this.roboHooverService = roboHooverService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RoboHooverRequestValidator());
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public RoboHooverResponse request(@RequestBody @Validated RoboHooverRequest request) {
		return roboHooverService.process(request);
	}

}
