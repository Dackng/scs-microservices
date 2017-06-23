package com.unmsm.psychological;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PsychologicalTestRepository extends CrudRepository<PsychologicalTest, Integer> {

	PsychologicalTest findPsychologicalTestByEmrHealthPlanIdAndEmrPatientCode(Integer emrHealthPlanId, Integer emrPatientCode);
	
	@Query("select case when (count(t) > 0)  then true else false end  "
			+ " from PsychologicalTest t where t.emrHealthPlanId = ?1 and t.emrPatientCode = ?2")
	boolean validateExistenceByEmrHealthPlanIdAndEmrPatientCode(Integer emrHealthPlanId,
			Integer emrPatientCode);
	
	@Query("select case when (count(t) > 0)  then true else false end from PsychologicalTest t "
			+ " where t.emrHealthPlanId = ?1 and t.emrPatientCode = ?2 and t.isFinished = ?3")
	boolean validateTestFinished(Integer emrHealthPlanId, Integer emrPatientCode, Boolean finished);
}
