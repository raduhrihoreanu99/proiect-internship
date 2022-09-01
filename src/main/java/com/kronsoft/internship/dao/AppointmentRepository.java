package com.kronsoft.internship.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kronsoft.internship.entities.Appointment;
import com.kronsoft.internship.entities.AppointmentStatus;
import com.kronsoft.internship.entities.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByPatient(Patient patient);
	
	List<Appointment> findByPatientPatientId(Long patientId);
	
	@Query("SELECT app FROM Appointment app WHERE app.appointmentStatus = :appointmentStatus")
	List<Appointment> findAllAppointmentsByAppointmentStatus(@Param("appointmentStatus") AppointmentStatus appointmentStatus);
	
	@Query(value = "SELECT * FROM appointment WHERE appointment_status = ?1", nativeQuery = true)
	List<Appointment> findAllAppointmentsByAppointmentStatusNative(AppointmentStatus appointmentStatus);
	
	List<Appointment> findByStartDateAfter(LocalDateTime after);
	
	List<Appointment> findByStartDateBetween(LocalDateTime from, LocalDateTime to);
	
	List<Appointment> findByAppointmentStatusNot(AppointmentStatus appointmentStatus);
	
	long countByAppointmentStatus(AppointmentStatus appointmentStatus);
	
	void deleteByAppointmentStatus(AppointmentStatus appointmentStatus);
	
	boolean existsByPatient(Patient patient);
	
	List<Appointment> findByPatientFirstName(String name);

}
