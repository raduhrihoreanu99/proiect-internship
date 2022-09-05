package com.kronsoft.internship;

import com.kronsoft.internship.service.AppointmentService;
import com.kronsoft.internship.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

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
		appointmentService.loadAndDisplayAppointments();
		patientService.loadAndDisplayPatients();
	}
}
