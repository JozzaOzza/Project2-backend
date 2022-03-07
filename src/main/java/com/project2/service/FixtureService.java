package com.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project2.domain.Fixture;
import com.project2.repo.FixtureRepo;

@Service
public class FixtureService implements ServiceInterface<Fixture>{

	private FixtureRepo repo;
	
	@Autowired
	public FixtureService(FixtureRepo repo) {
		super();
		this.repo = repo;
	}
	
	@Override
	public Fixture create(Fixture m) {
		Fixture created = this.repo.save(m);
		return created;
	}

	@Override
	public List<Fixture> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Fixture getOne(Long id) {
		Optional<Fixture> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Fixture update(Long id, Fixture m) {
		Fixture found = this.repo.findById(id).get();
		found.setStadium(m.getStadium());
		found.setConditions(m.getConditions());
		found.setTeamSize(m.getTeamSize());
		Fixture updated = this.repo.save(found);
		return updated;
	}

	@Override
	public void remove(@PathVariable Long id) {
		this.repo.deleteById(id);
	}

}
