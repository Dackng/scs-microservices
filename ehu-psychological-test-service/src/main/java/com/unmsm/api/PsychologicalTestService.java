package com.unmsm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unmsm.psychological.PsychologicalTest;
import com.unmsm.psychological.PsychologicalTestRepository;

@Service
public class PsychologicalTestService {

	PsychologicalTestRepository psychologicalTestRepository;
	
	@Autowired
	public PsychologicalTestService(PsychologicalTestRepository psychologicalTestRepository){
		this.psychologicalTestRepository = psychologicalTestRepository;
	}
	
	public PsychologicalTest registerPsychologicalTest(PsychologicalTest psychologicalTest){
		return psychologicalTestRepository.save(psychologicalTest);
	}
	
	public PsychologicalTest findPsychologicalTestByEmrHealthPlanIdAndEmrPatientCode(Integer emrHealthPlanId, Integer emrPatientCode){
		return psychologicalTestRepository.findPsychologicalTestByEmrHealthPlanIdAndEmrPatientCode(emrHealthPlanId, emrPatientCode);
	}
}
