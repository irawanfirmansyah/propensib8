package com.project.propensib8.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 10)
	@Column(name = "id_medrec", nullable = false, unique = true)
	private String idMedrec;
	
	@NotNull
	@Size(max = 16)
	@Column(name = "nik", nullable = false)
	private String nik;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "alamat", nullable = false)
	private String alamat;
	
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggalLahir;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempatLahir;
	
	@NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdMedrec() {
		return idMedrec;
	}

	public void setIdMedrec(String idMedrec) {
		this.idMedrec = idMedrec;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public int getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(int jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
}
