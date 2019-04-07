package com.project.propensib8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
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
	private Calendar tanggal;

    @NotNull
    @Range(min = 0, max = 3, message = "The value must be positive")
    @Column(name = "urgensi", nullable = false)
    private int urgensi;

	@NotNull
    @Column(name = "jenis_komplain", nullable = false)
    @Range(min = 0, max = 1, message = "The value must be positive")
    private int jenisKomplain;

	@NotNull
	@Column(name = "komplain", nullable = false)
	private String komplain;

	@NotNull
	@Column(name = "rating", nullable = false)
	private int rating;

	public KomplainModel(String komplain, Calendar tanggal, int jenisKomplain, int rating, int idPasien, int urgensi) {
        this.komplain = komplain;
        this.tanggal = tanggal;
        this.jenisKomplain = jenisKomplain;
        this.rating = rating;
        this.urgensi = urgensi;
    }

    public KomplainModel() {

    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

    public int getUrgensi() {
        return urgensi;
    }

    public String getKomplain() {
        return this.komplain;
    }

    public void setKomplain(String komplain) {
	    this.komplain = komplain;
    }

    public void setUrgensi(int urgensi) {
        this.urgensi = urgensi;
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
