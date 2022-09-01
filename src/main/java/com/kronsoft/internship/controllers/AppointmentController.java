package com.kronsoft.internship.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.internship.dto.AppointmentDto;
import com.kronsoft.internship.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	private static Logger logger = LoggerFactory.getLogger(AppointmentController.class);
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AppointmentDto> getAppointmentsByPatientId(@PathVariable Long id) {
		logger.info("[REST] GET call to /appointment/{}", id);
		return appointmentService.getAppointmentsByPatientId(id);
	}
	
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentDto createPatient(@RequestBody AppointmentDto appointmentDto,
			@RequestParam Long patientId) {
		logger.info("[REST] POST call to /appointment/create");
		return appointmentService.createAppointment(appointmentDto, patientId);
	}
}
