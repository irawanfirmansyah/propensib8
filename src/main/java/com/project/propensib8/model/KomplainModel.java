package com.project.propensib8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "komplain")
public class KomplainModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PasienModel pasien;

	@NotNull
	@Column(name = "tanggal", nullable = false)
	private Date jenisSurvei;
	
	@NotNull
    @Column(name = "jenis_komplain", nullable = false)
    private int jenisKomplain;

	@NotNull
	@Column(name = "komplain", nullable = false)
	private String komplain;

	@NotNull
	@Column(name = "rating", nullable = false)
	private int rating;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

    public String getKomplain() {
        return this.komplain;
    }

    public void setKomplain(String komplain) {
	    this.komplain = komplain;
    }

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getJenisKomplain() {
		return jenisKomplain;
	}

	public void setJenisKomplain(int jenisKomplain) {
		this.jenisKomplain = jenisKomplain;
	}

	public PasienModel getPasien() {
		return pasien;
	}

	public void setPasien(PasienModel pasien) {
		this.pasien = pasien;
	}
	
	
}
