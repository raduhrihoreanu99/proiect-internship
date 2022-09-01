package com.kronsoft.internship.dto;

import java.time.LocalDateTime;

import com.kronsoft.internship.entities.AppointmentStatus;

public class AppointmentDto {
	private Long appointmentId;
	private AppointmentStatus appointmentStatus;
	private LocalDateTime startDate;
	
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
}
