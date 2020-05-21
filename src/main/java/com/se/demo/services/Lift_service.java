package com.se.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.se.demo.models.Lift_model;
import com.se.demo.models.ResponseModel;

public class Lift_service {
	
	public static ResponseModel pickAndDrop(int from_req, int to_req) throws JsonProcessingException {
		
		ResponseModel response = new ResponseModel();
		
		Runnable r = new Lift(from_req, to_req);
		new Thread(r).start();
		response = Lift.getEta();
		
		return response;
	}

}
