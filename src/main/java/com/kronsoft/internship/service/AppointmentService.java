package com.kronsoft.internship.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kronsoft.internship.constants.FilterConstants;
import com.kronsoft.internship.dao.AppointmentRepository;
import com.kronsoft.internship.entities.Appointment;
import com.kronsoft.internship.entities.AppointmentStatus;
import com.kronsoft.internship.entities.Patient;

@Service
@Transactional
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void loadAndDisplayAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		System.out.println("--- APPOINTMENTS ---");
		System.out.println(appointments);
		
		Session session = entityManager.unwrap(Session.class);
		session.enableFilter(FilterConstants.NOT_EXPIRED_APPOINTMENTS);
		appointments = appointmentRepository.findAll();
		System.out.println("Non expired filters size: " + appointments.size());
		session.disableFilter(FilterConstants.NOT_EXPIRED_APPOINTMENTS);
	}

	public void loadAndDisplayAppointmentsForPatient(Patient patient) {
		List<Appointment> appointments = appointmentRepository.findByPatient(patient);
		if (appointments.isEmpty()) {
			appointments.add(createAppointment(patient));
		}
		System.out.println("Appointments for Patient: " + patient);
		System.out.println(appointments);
	}

	public void createAppointmentForPatient(Patient patient) {
		createAppointment(patient);
	}
	
	public void createAppointmentsForPatient(Patient patient, int nr) {
		for (int idx = 0; idx < nr; idx++) {
			createAppointment(patient);
		}
	}
	
	public boolean existsAppointmentForPatient(Patient patient) {
		return appointmentRepository.existsByPatient(patient);
	}
	
	public List<Appointment> getAppointmentsByPatientFirstName(String patientName) {
		return appointmentRepository.findByPatientFirstName(patientName);
	}
	
	private Appointment createAppointment(Patient patient) {
		Appointment appointment = new Appointment();
		appointment.setAppointmentStatus(AppointmentStatus.CREATED);
		appointment.setStartDate(LocalDateTime.now());
		appointment.setPatient(patient);
		return appointmentRepository.save(appointment);
	}

}
