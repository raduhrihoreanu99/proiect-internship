package com.kronsoft.internship.test;

import com.kronsoft.internship.entities.Patient;
import com.kronsoft.internship.service.PatientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

@SpringBootTest
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientServiceTest {

  public static final String HRIHOREANU = "Hrihoreanu";
  public static final String RADU = "Radu";
  public static final String CNP = "1990824080052";
  public static final String DAN = "Dan";
  public static final String SELARU = "Selaru";

  @Autowired
  private PatientService patientService;

  private Patient persistedPatient;
  private String persistedFirstName;
  private String persistedLastName;

  @BeforeAll
  public void createPatientTest() {
    Patient patient = new Patient();
    patient.setLastName(HRIHOREANU);
    patient.setFirstName(RADU);
    patient.setBirthdate(LocalDate.now().minusYears(23));
    patient.setCnp(CNP);

    persistedPatient = patientService.persistPatient(patient);
    Assertions.assertNotNull(persistedPatient);
    updatePersistedFirstAndLastName();
  }

  private void updatePersistedFirstAndLastName() {
    persistedFirstName = persistedPatient.getFirstName();
    persistedLastName = persistedPatient.getLastName();
  }

  @Test
  public void getPatientByCnpTest() {
    Patient patient = patientService.getPatientByCnp(CNP);
    Assertions.assertEquals(persistedFirstName, patient.getFirstName());
    Assertions.assertEquals(persistedLastName, patient.getLastName());
    Assertions.assertEquals(CNP, patient.getCnp());
  }

  @Test
  public void editPatientTest() {
    persistedPatient.setFirstName(DAN);
    persistedPatient.setLastName(SELARU);
    persistedPatient = patientService.persistPatient(persistedPatient);

    Assertions.assertEquals(DAN, persistedPatient.getFirstName());
    Assertions.assertEquals(SELARU, persistedPatient.getLastName());

    updatePersistedFirstAndLastName();
  }

  @AfterAll
  public void deletePatientTest() {
    Patient patient = patientService.getPatientByCnp(CNP);
    patientService.deletePatient(patient.getPatientId());

    Patient persistedPatient = patientService.getPatientByCnp(CNP);
    Assertions.assertNull(persistedPatient);
  }
}
