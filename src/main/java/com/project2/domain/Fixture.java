package com.project2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fixture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String stadium;
	
	@Column(nullable = false)
	private String conditions;
	
	@Column(nullable = false)
	private Long teamSize;
	
	public Fixture() {
		super();
	}



	public Fixture(Long id, String stadium, String conditions, Long teamSize) {
		super();
		this.id = id;
		this.stadium = stadium;
		this.conditions = conditions;
		this.teamSize = teamSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Long getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(Long teamSize) {
		this.teamSize = teamSize;
	}
	
	
	
}
