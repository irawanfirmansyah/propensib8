package com.project.propensib8.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "unit")
public class UnitModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_survei", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private SurveiModel survei;
	
	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ParameterModel> listParameter;
	
	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FeedbackModel> listFeedback;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public SurveiModel getSurvei() {
		return survei;
	}

	public void setSurvei(SurveiModel survei) {
		this.survei = survei;
	}

	public List<ParameterModel> getListParameter() {
		return listParameter;
	}

	public void setListParameter(List<ParameterModel> listParameter) {
		this.listParameter = listParameter;
	}

	public List<FeedbackModel> getListFeedback() {
		return listFeedback;
	}

	public void setListFeedback(List<FeedbackModel> listFeedback) {
		this.listFeedback = listFeedback;
	}
}

