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

public class Lift implements Runnable {

	private int fromFloor;
	private int toFloor;
	
	private int lift1Floor;
	private int lift2Floor;

	private int eta;

	public static Lift_model lift1 = new Lift_model();
	public static Lift_model lift2 = new Lift_model();

	static ResponseModel response = new ResponseModel();

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json;

	private static final Logger logger = LogManager.getLogger(Lift.class);

	public Lift(int fromFloor, int toFloor) {

		this.fromFloor = fromFloor;
		this.toFloor = toFloor;

	}

	public void run() {
		
		List<String> directionList = new ArrayList<String>();
		directionList.add("NAN");
		directionList.add("UP");
		directionList.add("DOWN");


		List<String> stateList = new ArrayList<String>();
		stateList.add("IDLE");
		stateList.add("TO_PICKUP");
		stateList.add("PICKUP");
		stateList.add("TO_DROPOFF");
		stateList.add("DROPOFF");

	
		if (lift1.getState() == null) {
			lift1.setState(stateList.get(0));
			lift1.setDirection(directionList.get(0));
		}

		if (lift2.getState() == null) {
			lift2.setState(stateList.get(0));
			lift2.setDirection(directionList.get(0));
		}

		if (lift1.getState().equals(stateList.get(0))) {

			if (lift1Floor == 0) {
				lift1Floor = 1;
			}

			lift1.setLiftId(1);
			lift1.setLiftFloor(lift1Floor);

			eta = FromToTime(lift1Floor, fromFloor);
			response.setETA(eta);

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json.toString());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			lift1.setState(stateList.get(1));

			if (lift1.getState().equals(stateList.get(0)) || lift1.getState().equals(stateList.get(1))) {
				lift1.setPerson(0);
			} else {
				lift1.setPerson(1);
			}

			if ((fromFloor - lift1Floor) > 0) {
				lift1.setDirection(directionList.get(1));
			} else if ((fromFloor - lift1Floor) < 0) {
				lift1.setDirection(directionList.get(2));
			}

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(eta * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift1Floor = fromFloor;
			lift1.setLiftFloor(lift1Floor);
			lift1.setState(stateList.get(2));
			lift1.setDirection(directionList.get(0));

			if (lift1.getState().equals(stateList.get(0)) || lift1.getState().equals(stateList.get(1))) {
				lift1.setPerson(0);
			} else {
				lift1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift1.setState(stateList.get(3));
			lift1.setLiftFloor(lift1Floor);

			if ((toFloor - lift1Floor) > 0) {
				lift1.setDirection(directionList.get(1));
			} else if ((toFloor - lift1Floor) < 0) {
				lift1.setDirection(directionList.get(2));
			}

			if (lift1.getState().equals(stateList.get(0)) || lift1.getState().equals(stateList.get(1))) {
				lift1.setPerson(0);
			} else {
				lift1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					int time = FromToTime(fromFloor, toFloor);
					Thread.sleep(time * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift1Floor = toFloor;
			lift1.setState(stateList.get(4));
			lift1.setLiftFloor(lift1Floor);
			lift1.setDirection(directionList.get(0));

			if (lift1.getState().equals(stateList.get(0)) || lift1.getState().equals(stateList.get(1))) {
				lift1.setPerson(0);
			} else {
				lift1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift1.setState(stateList.get(0));
			lift1.setDirection(directionList.get(0));

			if (lift1.getState().equals(stateList.get(0)) || lift1.getState().equals(stateList.get(1))) {
				lift1.setPerson(0);
			} else {
				lift1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (lift2.getState().equals(stateList.get(0))) {

			if (lift2Floor == 0) {
				lift2Floor = 1;
			}

			lift2.setLiftId(2);
			lift2.setLiftFloor(lift1Floor);

			eta = FromToTime(lift2Floor, fromFloor);
			response.setETA(eta);

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			lift1.setState(stateList.get(1));

			if ((fromFloor - lift2Floor) > 0) {
				lift1.setDirection(directionList.get(1));
			} else if ((fromFloor - lift2Floor) < 0) {
				lift1.setDirection(directionList.get(2));
			}

			if (lift2.getState().equals(stateList.get(0)) || lift2.getState().equals(stateList.get(1))) {
				lift2.setPerson(0);
			} else {
				lift2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(eta * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift2Floor = fromFloor;
			lift2.setLiftFloor(lift2Floor);
			lift2.setState(stateList.get(2));
			lift2.setDirection(directionList.get(0));

			if (lift2.getState().equals(stateList.get(0)) || lift2.getState().equals(stateList.get(1))) {
				lift2.setPerson(0);
			} else {
				lift2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift2.setState(stateList.get(3));
			lift2.setLiftFloor(lift2Floor);

			if ((toFloor - lift2Floor) > 0) {
				lift2.setDirection(directionList.get(1));
			} else if ((toFloor - lift2Floor) < 0) {
				lift2.setDirection(directionList.get(2));
			}

			if (lift2.getState().equals(stateList.get(0)) || lift2.getState().equals(stateList.get(1))) {
				lift2.setPerson(0);
			} else {
				lift2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					int time = FromToTime(fromFloor, toFloor);
					Thread.sleep(time * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift2Floor = toFloor;
			lift2.setState(stateList.get(4));
			lift2.setLiftFloor(lift1Floor);
			lift2.setDirection(directionList.get(0));

			if (lift2.getState().equals(stateList.get(0)) || lift2.getState().equals(stateList.get(1))) {
				lift2.setPerson(0);
			} else {
				lift2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift2.setState(stateList.get(0));
			lift2.setDirection(directionList.get(0));

			if (lift2.getState().equals(stateList.get(0)) || lift2.getState().equals(stateList.get(1))) {
				lift2.setPerson(0);
			} else {
				lift2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ResponseModel getEta() {
		return response;
	}

	public int FromToTime(int cur, int to) {
		int number = (to - cur) * 3;
		
		if(number<0) {
			return -1*number;
		}
		
		else {
		
		return number;
		}
	}

}
