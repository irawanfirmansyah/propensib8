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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "komplain")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KomplainModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@Size(max = 255)
	@Column(name = "result", nullable = false)
	private String result;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_survei", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private SurveiModel survei;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unit", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UnitModel unit;
	
	@Column(name = "is_solved", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isSolved;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public UnitModel getUnit() {
		return unit;
	}
	
	public void setUnit(UnitModel unit) {
		this.unit = unit;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	public SurveiModel getSurvei() {
		return survei;
	}

	public void setSurvei(SurveiModel survei) {
		this.survei = survei;
	}
}
