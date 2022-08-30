package com.kronsoft.internship.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.kronsoft.internship.entities.Patient;

public class PatientDto {
	private Long patientId;
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private String cnp;
	
	public PatientDto() {
		
	}
	
	public PatientDto(Patient patient) {
		BeanUtils.copyProperties(patient, this, "appointments");
	}
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
}
