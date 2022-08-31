package com.kronsoft.internship.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.internship.dto.PatientDto;
import com.kronsoft.internship.entities.Patient;
import com.kronsoft.internship.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	private static Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private PatientService patientService; 
	
	@PostConstruct
	private void test() {
		logger.info("-----------------PATIENT CONTROLLER-------------------------");
		logger.info("Aceasta este o informatie!");
		logger.warn("Acesta este un avertisment!");
		logger.error("Aceasta este o eroare!");
		
		logger.trace("TRACE MESSAGE");
		logger.debug("DEBUG MESSAGE");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDto> getAllPatients() {
		return patientService.getAllPatients()
				.stream()
				.map(patient -> new PatientDto(patient))
				.collect(Collectors.toList());
	}
	
//	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
//		try {
//			return new ResponseEntity<>(patientService.persistPatient(patient), HttpStatus.OK);
//		} catch (DataIntegrityViolationException e) {
//			return new ResponseEntity("Object is missing required property", HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			return new ResponseEntity("Unkown Error!", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		return patientService.persistPatient(patient);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient updatePatient(@RequestBody Patient patient) {
		return patientService.persistPatient(patient);
	}
	
	@DeleteMapping(value = "/delete/{patient_id}")
	public void deletePatient(@PathVariable(name = "patient_id") Long id) {
		patientService.deletePatient(id);
	}
	
	@GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDto> getPatientsByFirstName(@RequestParam(required = false) String firstName,
			@RequestParam(required = false, defaultValue = "Wisoky") String lastName) {
		return patientService.getPatientsByName(firstName, lastName).stream()
				.map(patient -> new PatientDto(patient))
				.collect(Collectors.toList());
	}
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<String> dataIntegrityException(DataIntegrityViolationException e) {
//		return new ResponseEntity<>("Data integrity violation! Some required property may be missing", HttpStatus.BAD_REQUEST);
//	}
	
}
