package com.kronsoft.internship.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.kronsoft.internship.constants.FilterConstants;
import com.kronsoft.internship.validators.CnpConstraint;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "patients")
@Filter(name = FilterConstants.PATIENTS_OVER_EIGHTEEN)
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PATIENT_ID")
	private Long patientId;

	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 40, nullable = false)
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2022-09-02")
	@NotNull(message = "Birthdate cannot be null!")
	private LocalDate birthdate;
	
	@CnpConstraint
	@NotBlank(message = "CNP cannot be blank!")
	@Column(nullable = false, length = 13, unique = true)
	private String cnp;

	/* tipuri de fetch pt relatie: LAZY - aduce relatie doar cand este folosita,
	 * EAGER - aduce relatia cand se incarca obiectul.
	  Daca tipul de fetch este LAZY, in momentul accesarii campului, trebuie sa ne aflam in interiorul unei tranzactii.
	  Atentie la problema recursivitatii: patient - appointments - patient - appointments - etc.
	  Rezolvari: 1. Ne configuram relatia ca fiind unidirectionala si aducem entitatea corespondenta la cerere.
	             2. Folosim o noua clasa, care, reprezinta structural (tipuri si nume de fields) entitatea noastra, dar care nu contine si relatia.
	                  Vezi in PatientService explicatia la query-ul folosind DTO.
	*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.REMOVE)
	private List<Appointment> appointments;

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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthdate, cnp, firstName, lastName, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(birthdate, other.birthdate) && Objects.equals(cnp, other.cnp)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(patientId, other.patientId);
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthdate=" + birthdate + ", cnp=" + cnp + "]";
	}

}
