package com.project.propensib8.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
		    if(komplain.getSurvei().getRating() <= 3 && komplain.isSolved() == false){
		        listOfKomplain.add(komplain);
            }
        }
	    return listOfKomplain;
	}

    @Override
    public List<KomplainModel> findAllSolvedKomplain() {
	    List<KomplainModel> listOfKomplain = new ArrayList<>();
	    for(KomplainModel komplain : komplainDb.findAll()){
	        if(komplain.isSolved() == true){
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
		List<KomplainModel> listOfReview = komplainDb.findAll();
		int counter = 0;
		if (komplainDb.findAll().size() <= 3 && komplainDb.findAll().size() >0) {
			for (KomplainModel komplain : komplainDb.findAll()) {
				if (komplain.getUnit().getNama().equalsIgnoreCase(nama)) {
					list.add(komplain.getSurvei().getPasien().getNama()+","+komplain.getDeskripsi());
					System.out.println(komplain.getSurvei().getPasien().getNama()+","+komplain.getDeskripsi());
				}
			}
		} else {
			for (KomplainModel komplain : komplainDb.findAll()) {
				if(counter != 3) {
					if (komplain.getUnit().getNama().equalsIgnoreCase(nama)) {
						list.add(komplain.getSurvei().getPasien().getNama()+","+komplain.getDeskripsi());
						counter ++;
					}
				}
				else {
					break;
				}
			}
		}
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(list.get(i));
		}
		return list;
	}

    @Override
    public List<String> getDeskripsiKomplainByNama(String nama) {
        List<String> list = new ArrayList<>();
        List<KomplainModel> listOfReview = komplainDb.findAll();
        if (komplainDb.findAll().size() <= 3) {
            for (KomplainModel komplain : komplainDb.findAll()) {
                if (komplain.getUnit().getNama().equalsIgnoreCase(nama)) {
                    list.add(komplain.getDeskripsi());
                }
            }
        } else {
            for (KomplainModel komplain : komplainDb.findAll()) {
                if (komplain.getUnit().getNama().equalsIgnoreCase(nama)) {
                    list.add(komplain.getDeskripsi());
                }
            }
        }
        return list;
    }

    public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}
}
