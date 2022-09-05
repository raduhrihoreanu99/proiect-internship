package com.kronsoft.internship.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kronsoft.internship.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	@Query("SELECT p FROM Patient p")
	List<Patient> findAllPatients();
	
	List<Patient> findByFirstNameOrderByCnp(String firstName);
	
	List<Patient> findByLastNameOrderByCnpDesc(String lastName);
	
	List<Patient> findByFirstName(String firstName);
	
	List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Patient> findByFirstNameIn(List<String> firstNames);
	
	List<Patient> findByLastNameLike(String lastName);
	
	Patient findByCnp(@Param("cnp") String cnp);
	
	boolean existsByCnp(String cnp);
	
	void deleteByCnp(String cnp);

}
