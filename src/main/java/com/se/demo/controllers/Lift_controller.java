package com.se.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.se.demo.models.PersonModel;
import com.se.demo.models.ResponseModel;
import com.se.demo.services.Lift_service;

@RestController
public class Lift_controller {

	@RequestMapping(method = RequestMethod.POST, value = "/smartkent/liftsimulation/")
	public ResponseModel pickAndDrop(@RequestBody PersonModel request) throws SQLException, IOException, InterruptedException 
	{
		
		ResponseModel response = Lift_service.pickAndDrop(request.getFromFloor(), request.getToFloor());
		return response;
	}
}
