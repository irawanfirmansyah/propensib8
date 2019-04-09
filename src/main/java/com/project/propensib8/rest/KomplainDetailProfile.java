package com.project.propensib8.rest;

import java.util.List;

import com.project.propensib8.model.KomplainModel;

public class KomplainDetailProfile {
	String namaPasien;
	String tanggalPengisian;
	String alamat;
	List<KomplainModel> listKomplain;
	
	public String getNamaPasien() {
		return namaPasien;
	}
	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}
	public String getTanggalPengisian() {
		return tanggalPengisian;
	}
	public void setTanggalPengisian(String tanggalPengisian) {
		this.tanggalPengisian = tanggalPengisian;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public List<KomplainModel> getListKomplain() {
		return listKomplain;
	}
	public void setListKomplain(List<KomplainModel> listKomplain) {
		this.listKomplain = listKomplain;
	}
	
	
}
