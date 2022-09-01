package com.kronsoft.internship.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kronsoft.internship.constants.FilterConstants;
import com.kronsoft.internship.dao.AppointmentRepository;
import com.kronsoft.internship.dao.PatientRepository;
import com.kronsoft.internship.dto.AppointmentDto;
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
	
	@Autowired
	private PatientRepository patientRepository;
	
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
	
	public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
		return appointmentRepository.findByPatientPatientId(patientId)
				.stream().map(this::convertToDto).collect(Collectors.toList());
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
	
	public AppointmentDto createAppointment(AppointmentDto appointmentDto, Long patientId) {
		Appointment appointment = convertToAppoinment(appointmentDto);
		patientRepository.findById(patientId).ifPresentOrElse(p -> {
			appointment.setPatient(p);
		}, () -> new Exception("Patient with id " + patientId.toString() + " not found!"));
		return convertToDto(appointmentRepository.save(appointment));
		
	}
	
	private Appointment createAppointment(Patient patient) {
		Appointment appointment = new Appointment();
		appointment.setAppointmentStatus(AppointmentStatus.CREATED);
		appointment.setStartDate(LocalDateTime.now());
		appointment.setPatient(patient);
		return appointmentRepository.save(appointment);
	}
	
	private AppointmentDto convertToDto(Appointment appointment) {
		AppointmentDto appointmentDto = new AppointmentDto();
		BeanUtils.copyProperties(appointment, appointmentDto);
		return appointmentDto;
	}
	
	private Appointment convertToAppoinment(AppointmentDto appointmentDto) {
		Appointment appointment = new Appointment();
		BeanUtils.copyProperties(appointmentDto, appointment);
		return appointment;
	}

}
