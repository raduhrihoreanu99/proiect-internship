package com.kronsoft.internship;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kronsoft.internship.service.AppointmentService;
import com.kronsoft.internship.service.PatientService;

@SpringBootApplication
public class JavaInternshipData {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaInternshipData.class, args);
	}
	
	@PostConstruct
	private void init() {
		patientService.loadAndDisplayPatients();
		appointmentService.loadAndDisplayAppointments();
	}

}
