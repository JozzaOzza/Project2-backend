package com.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.domain.Fixture;

public interface FixtureRepo extends JpaRepository<Fixture, Long>{

}