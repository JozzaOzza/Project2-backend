package com.project2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.domain.Fixture;
import com.project2.service.FixtureService;

@RestController
public class FixtureController {

	private FixtureService matchService;
	
	@Autowired
	public FixtureController(FixtureService matchService) {
		super();
		this.matchService = matchService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Fixture> createMatch(@RequestBody Fixture m) {
		Fixture created = this.matchService.create(m);
		ResponseEntity<Fixture> response = new ResponseEntity<Fixture>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Fixture>> getAllMatches() {
		return ResponseEntity.ok(this.matchService.getAll());
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Fixture> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.matchService.getOne(id));
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Fixture> updateMatch(@PathVariable Long id, @RequestBody Fixture m) {
		Fixture body = this.matchService.update(id, m);
		ResponseEntity<Fixture> response = new ResponseEntity<Fixture>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeMatch(@PathVariable Long id) {
		this.matchService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
