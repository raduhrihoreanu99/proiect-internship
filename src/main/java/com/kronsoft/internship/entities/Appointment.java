package com.kronsoft.internship.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import com.kronsoft.internship.constants.FilterConstants;

@Entity
@Table(name = "appointments")
@Filter(name = FilterConstants.NOT_EXPIRED_APPOINTMENTS)
public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 578159756497042876L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "APPOINTMENT_ID")
	private Long appointmentId;

	@Column(name = "APPOINTMENT_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;

	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")
	private Patient patient;

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, appointmentStatus, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentId, other.appointmentId) && appointmentStatus == other.appointmentStatus
				&& Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentStatus=" + appointmentStatus
				+ ", startDate=" + startDate + "]";
	}

}
