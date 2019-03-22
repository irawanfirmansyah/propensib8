package com.project.propensib8.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "survei")
public class SurveiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
    @Column(name = "rating", nullable = false)
    private int rating;
	
	@OneToMany(mappedBy = "survei", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UnitModel> listUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<UnitModel> getListUnit() {
		return listUnit;
	}

	public void setListUnit(List<UnitModel> listUnit) {
		this.listUnit = listUnit;
	}
}
