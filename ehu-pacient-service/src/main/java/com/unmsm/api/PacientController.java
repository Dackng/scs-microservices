package com.unmsm.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.pacient.Pacient;

@RestController
@RequestMapping(path = "/api")
public class PacientController {
	
	private PacientService pacientService;
	
	@Autowired
	public PacientController(PacientService pacientService){
		this.pacientService = pacientService;
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public ResponseEntity<Pacient> savePacient(@RequestBody Pacient pacient) throws Exception{
		assert pacient != null;
		return Optional.ofNullable(pacientService.savePacient(pacient))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Could not save pacient"));
	}
	
	@RequestMapping(path = "/find/{code}", method = RequestMethod.GET, name = "findPacientByCode")
	public ResponseEntity<Pacient> findPacientByCode(@PathVariable("code") String code){
		return Optional.ofNullable(pacientService.findPacientByCode(Long.parseLong(code)))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}