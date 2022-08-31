package com.kronsoft.internship.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.kronsoft.internship.dao.PatientRepository;
import com.kronsoft.internship.entities.Patient;

@Service
//aplica @Transactional pe toate metodele clasei. Default, porneste o tranzactie daca nu exista una, sau o foloseste pe cea existenta.
// Atentie, pentru ca Spring sa ia in considerare annotarea @Transactional, metoda respectiva trebuie chemata dintr-o alta clasa Spring (ex: clasa cu metoda main, un alt service, etc.)
@Transactional 
public class PatientService {
	private static Logger logger = LoggerFactory.getLogger(PatientService.class);

	// Constanta folosita pentru generarea aleatorie de valori
	private static final Random RANDOM = new Random();
	
	// Vezi libraria Javafaker, folosita pentru a genera date realiste (ex: prenume, data de nastere)
	private final Faker faker = new Faker();

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@PostConstruct
	private void test() {
		logger.info("-----------------PATIENT SERVICE-------------------------");
		logger.info("Aceasta este o informatie!");
		logger.warn("Acesta este un avertisment!");
		logger.error("Aceasta este o eroare!");
		
		logger.trace("TRACE MESSAGE");
		logger.debug("DEBUG MESSAGE");
	}
	
	public void loadAndDisplayPatients() {
		// daca nu exista niciun pacient, creem, altfel ii aducem pe toti din DB
		List<Patient> patients = patientRepository.count() == 0 ? createPatients(10)
				: patientRepository.findAll();
		System.out.println("--- PATIENT LIST ---");
		System.out.println(patients);
		System.out.println("Patients over 18: " +patients.size());
		
		for (Patient patient : patients) {
			if (!appointmentService.existsAppointmentForPatient(patient)) {
				appointmentService.createAppointmentForPatient(patient);
			}
		}
		
		// Pentru fiecare pacient, afisam din DB lista de appointmenturi
		patients.forEach(patient -> appointmentService.loadAndDisplayAppointmentsForPatient(patient));

		/* Afisam pentru fiecare pacient appointmenturile, populate ca parte a relatiei.
		* Atentie! Fetchtype-ul relatiei fiind LAZY, acestea se aduc doar in momentul in care le accesam,
		* in cazul acesta, prin apelarea getterului. Relatia APPOINTMENT -> PATIENT este la randul ei LAZY,
		* ceea ce ne permite sa evitam o recursivitate. (appointment.patient nu este apelat nicaieri, nefacand parte nici din metoda toString)
		* Alternativa: relatie unidirectionala
		 */
		patients.forEach(patient -> System.out.println(patient.getAppointments()));
	}
	
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	public Patient persistPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public void deletePatient(Long patientId) {
		patientRepository.deleteById(patientId);
	}
	
	public List<Patient> getPatientsByName(String firstName, String lastName) {
		if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
			return patientRepository.findAll();
		} else if (!Objects.isNull(firstName) && !Objects.isNull(lastName)) {
			return patientRepository.findByFirstNameAndLastName(firstName, lastName);
		} else if (Objects.isNull(lastName)) {
			return patientRepository.findByFirstName(firstName);
		} else {
			return patientRepository.findByLastNameLike(lastName);
		}
	}
	
	// Creaza n patienti, in functie de argumentul primit
	private List<Patient> createPatients(int nr) {
		if (nr <= 0) {
			return List.of();
		}
		
		List<Patient> patients = new ArrayList<>();
		for (int idx = 0; idx < nr; idx++) {
			patients.add(createPatient());
		}
		return patientRepository.saveAll(patients);
	}

	private Patient createPatient() {
		Patient patient = new Patient();
		patient.setFirstName(faker.name().firstName());
		patient.setLastName(faker.name().lastName());
		patient.setBirthdate(generateBirthdate());
		patient.setCnp(generateCnp());
		return patient;
	}
	
	/* Obiectul data de nastere generat este de tip java.util.Date (tip vechi) 
	  si trebuie convertit la LocalDate */ 
	private LocalDate generateBirthdate() {
		return faker.date().birthday()
				.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	// Generare CNP. Folosind constanta de random, generam un long de 13 cifre.
	// 1e12 = 1_000_000_000_000;
	private String generateCnp() {
		long thirteenDigitsNr = Math.round(1e12);
		long cnp = RANDOM.nextLong(thirteenDigitsNr, thirteenDigitsNr * 10);
		return String.valueOf(cnp);
	}

}
