package com.project.propensib8.service;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

=======
import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.KomplainDB;
>>>>>>> aca69f684d7b8cc926d9d505a70fd0b96369208a
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.ParameterDB;
=======
import java.util.List;
import java.util.Map;
>>>>>>> aca69f684d7b8cc926d9d505a70fd0b96369208a

@Service
@Transactional
public class KomplainServiceImpl implements KomplainService{

<<<<<<< HEAD
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
        return null;
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
}
=======
	@Autowired
	KomplainDB komplainDb;

	@Override
	public KomplainModel getKomplainById(long id) {
		return komplainDb.findById(id);
	}

	@Override
	public List<KomplainModel> findAll() {
		return komplainDb.findAll();
	}

	public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}

}
>>>>>>> aca69f684d7b8cc926d9d505a70fd0b96369208a
