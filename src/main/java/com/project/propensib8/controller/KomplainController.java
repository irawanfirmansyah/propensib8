package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.rest.BaseResponse;
import com.project.propensib8.rest.DetailKomplain;
import com.project.propensib8.rest.KomplainPasien;
import com.project.propensib8.rest.KomplainResponse;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.SurveiService;
import com.project.propensib8.service.UnitService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/komplain")
public class KomplainController {
	@Autowired
	private KomplainDB komplainDb;
	@Autowired
	private KomplainService komplainService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private SurveiService surveiService;

	@GetMapping(value = "/{namaKomplain}")
	public ResponseEntity<?> getKomplainByName(@PathVariable("namaKomplain") String namaKomplain) {
		List<KomplainModel> listKomplain = komplainDb.findAll();
		return new ResponseEntity(listKomplain, HttpStatus.OK);
	}

	@RequestMapping("/{id}")
	public KomplainModel getPasienById(@PathVariable("id") long id) {
		return komplainService.getKomplainById(id);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> createKomplain(@Valid @RequestBody Map<String, String> res)
			throws URISyntaxException, ParseException {

		KomplainModel result = new KomplainModel();
		SurveiModel surveiModel = surveiService.getSurveiById(res.get("idSurvei"));
		UnitModel unitModel = unitService.getUnitByName(res.get("namaUnit"));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		java.util.Date parsed = format.parse(res.get("tanggal"));
		Date dateSql = new java.sql.Date(parsed.getTime());
		System.out.println(dateSql);
		result.setTanggal(dateSql);
		result.setDeskripsi(res.get("deskripsi"));
		result.setUnit(unitModel);
		result.setSurvei(surveiModel);
		result.setSolvedMarketing(false);
		result.setResult("");
		result = komplainDb.save(result);
		BaseResponse<KomplainModel> response = new BaseResponse<>();

		if (result.getId() != 0) {
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(result);
		}

		// return ResponseEntity.created(new URI("/survei/add/" +
		// result.getId())).body(result);
		return ResponseEntity.created(new URI("/survei")).body(response);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllKomplains() {
		List<KomplainPasien> res = new ArrayList<>();
		List<SurveiModel> surveiKomplains = surveiService.getAllKomplain();
		System.out.println(surveiKomplains.size());
		for (SurveiModel s : surveiKomplains) {
			// kalo komplain ini blm di selesaikan marketing,
			// survei tsb masuk ke dalam list komplain pasien
			if (!s.getListKomplain().get(0).isSolvedMarketing()) {
				KomplainPasien komplainPasien = new KomplainPasien();
				komplainPasien.setIdSurvei(Long.toString(s.getId()));
				komplainPasien.setPasien(s.getPasien());
				List<KomplainResponse> listKomplainRes = new ArrayList<>();
				for(KomplainModel k : s.getListKomplain()){
					KomplainResponse komplainResponse = new KomplainResponse();
					komplainResponse.setDeskripsi(k.getDeskripsi());
					komplainResponse.setId(Long.toString(k.getId()));
					komplainResponse.setNamaUnit(k.getUnit().getNama());
					komplainResponse.setTanggal(k.getTanggal().toString());
					listKomplainRes.add(komplainResponse);
				}
				komplainPasien.setListKomplain(listKomplainRes);
				komplainPasien.setRating(s.getRating());
				res.add(komplainPasien);
			}
		}

		return new ResponseEntity(res, HttpStatus.OK);
	}

	@GetMapping(value = "/{idKomplain}")
	public ResponseEntity<?> getDetailKomplain(@PathVariable("idKomplain") String idKomplain) {
		KomplainModel komplain = komplainDb.findById(Long.parseLong(idKomplain));
		DetailKomplain detailKomplain = new DetailKomplain();
		detailKomplain.setAlamat(komplain.getSurvei().getPasien().getAlamat());
		detailKomplain.setDeskripsi(komplain.getDeskripsi());;
		detailKomplain.setNamaPasien(komplain.getSurvei().getPasien().getNama());
		detailKomplain.setNomorHp(komplain.getSurvei().getPasien().getNomorHp());
		detailKomplain.setNomorTelepon(komplain.getSurvei().getPasien().getNomorTelepon());
		detailKomplain.setTanggal(komplain.getTanggal().toString());

		return new ResponseEntity(detailKomplain, HttpStatus.OK);
	}

	// @GetMapping(value = "/riwayat/{namaUnit}/{tipeSurvei}")
	// public ResponseEntity<?>
	// getRiwayatKomplainByNamaUnit(@PathVariable("namaUnit") String namaUnit,
	// @PathVariable("tipeSurvei") String tipeSurvei,
	// @RequestParam("bulanTahunStart") @DateTimeFormat(iso =
	// DateTimeFormat.ISO.DATE) String tanggalMulais,
	// @RequestParam("bulanTahunEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	// String tanggalSelesais) {
	// Date tanggalMulai = Date.valueOf(tanggalMulais);
	// Date tanggalSelesai = Date.valueOf(tanggalSelesais);

	// }
}
