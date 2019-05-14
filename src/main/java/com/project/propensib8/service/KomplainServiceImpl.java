package com.project.propensib8.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.project.propensib8.rest.KomplainRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class KomplainServiceImpl implements KomplainService{

	@Autowired
	KomplainDB komplainDb;

	@Autowired
	PasienService pasienService;

	@Override
	public PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian) {
		PasienModel picked = new PasienModel();
		for (KomplainModel komplain: komplainDb.findAll()){
			if(komplain.getSurvei().getPasien().getNama().equals(namaPasien)){
				if(komplain.getSurvei().getTanggal().equals(tanggalPengisian)){
					picked = komplain.getSurvei().getPasien();
				}
			}
		}
		return picked;
	}

	@Override
	public KomplainModel getKomplainByNamaPasien(String namaPasien) {
		KomplainModel picked = null;
		for(KomplainModel komplain: komplainDb.findAll()) {
			if (komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien)) {
				picked = komplain;
			}
		}
		return picked;
	}

	@Override
	public List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian) {
		String tanggalPicked = "";
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain: komplainDb.findAll()){

			java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			tanggalPicked = formatter.format(sqlDate);

			if(komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien) && tanggalPicked.equalsIgnoreCase(tanggalPengisian)){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public KomplainModel getKomplainById(long id) {
		return komplainDb.findById(id);
	}

	@Override
	public List<KomplainModel> findAll() {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getSurvei().getRating() <= 3 && komplain.isSolvedMarketing() == false){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public List<KomplainModel> findAllSolvedKomplain() {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.isSolvedMarketing() == true){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public List<KomplainModel> getKomplainByNama(String nama) {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama)){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public int countKomplainByNama(String nama) {
		int count = 0;
		List<KomplainModel> komplains = komplainDb.findAll();
		for(int i=0 ; i<komplains.size() ; i++){
			if(komplains.get(i).getUnit().getNama().equalsIgnoreCase(nama)){
				count++;
				System.out.println("asd");
			}
		}
		System.out.println(komplains.size());
		return count;
	}

	@Override
	public int countRatingByNama(String nama) {
		int count = 0;
		int denominator = 0;
		for(int num = 0; num < komplainDb.findAll().size(); num++){
			if(komplainDb.findAll().get(num).getUnit().getNama().equalsIgnoreCase(nama)) {
				count += komplainDb.findAll().get(num).getSurvei().getRating();
				denominator += 1;
			}
		}
		count /= denominator;
		return count;
	}

	@Override
	public int countSolvedComplaints(String namaUnit) {
		int count = 0;
		List<KomplainModel> komplains = komplainDb.findAll();
		for(int i=0 ; i<komplains.size() ; i++){
			if(komplains.get(i).getUnit().getNama().equalsIgnoreCase(namaUnit)){
				KomplainModel k = komplains.get(i);
				if(k.isSolvedMarketing()) {
					count++;
				}
			}
		}
		System.out.println(count);
		return count;
	}

	@Override
	public List<KomplainModel> getKomplainByNamaUnit(String nama) {
		List<KomplainModel> list = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama)){
				list.add(komplain);
			}
		}
		return list;
	}

	@Override
	public List<String> getNamaPasienKomplainByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<KomplainModel> listOfKomplain = komplainDb.findAll();
		for(KomplainModel komplain : listOfKomplain) {
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama) && komplain.isSolvedMarketing() == true){
				list.add(komplain.getSurvei().getPasien().getNama());
			}
		}
		return list;
	}

	@Override
	public List<String> getDeskripsiKomplainByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<KomplainModel> listOfKomplain = komplainDb.findAll();
		for(KomplainModel komplain : listOfKomplain) {
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama) && komplain.isSolvedMarketing() == true){
				list.add(komplain.getDeskripsi());
			}
		}
		return list;
	}

	@Override
	public List<KomplainRest> createKomplainRest(String namaUnit) {
		List<KomplainRest> list = new ArrayList<>();
		for(KomplainModel komplain: komplainDb.findAll()){
			if(komplain.isSolvedMarketing() == true && komplain.getUnit().getNama().equalsIgnoreCase(namaUnit)) {
				KomplainRest komplainRest = new KomplainRest();
				komplainRest.setNama(komplain.getSurvei().getPasien().getNama());
				komplainRest.setDeskripsi(komplain.getDeskripsi());
				komplainRest.setRating(komplain.getSurvei().getRating());

				java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String resDate = formatter.format(sqlDate);
				komplainRest.setTanggalIsi(resDate);

				list.add(komplainRest);
			}
		}
		return list;
	}

	public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}
}
