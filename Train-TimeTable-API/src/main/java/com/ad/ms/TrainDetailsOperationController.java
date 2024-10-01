package com.ad.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.entity.TrainDetails;
import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;
import com.ad.service.ITrainDetailsMgmtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/timetable/api")
public class TrainDetailsOperationController {

	@Autowired
	private ITrainDetailsMgmtService trainService;
	
	@GetMapping("/category")
	public ResponseEntity<?> fetchTrainCategories(){
		try {
			Set<String> trainsInfo=trainService.showAllTrainCategories();
			return new ResponseEntity<Set<String>>(trainsInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/source-station")
	public ResponseEntity<?> fetchSourceStation(){
		try {
			Set<String>sourceStationInfo=trainService.showAllSourceStation();
			return new ResponseEntity<Set<String>>(sourceStationInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/destination-station")
	public ResponseEntity<?> fetchDestinationStation(){
		try {
			Set<String>destStationInfo=trainService.showAllDestinationStation();
			return new ResponseEntity<Set<String>>(destStationInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/active-days")
	public ResponseEntity<?> fetchActiveDays(){
		try {
			Set<String>activeDaysInfo=trainService.showAllActiveDays();
					return new ResponseEntity<Set<String>>(activeDaysInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/pdf-timetable")
	public void showPdfTimeTable(@RequestBody SearchInputs inputs, HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attcahment;fileName=Time-Table.pdf");
			trainService.generateTimetablePdfReport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/excel-timetable")
	public void showExcelReport(@RequestBody SearchInputs inputs, HttpServletResponse res){
		try {
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attcahment;fileName=Time-Table.xls");
			trainService.generateTimetableExcelReport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-pdf-timetable")
	public void showAllPdfReport(HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attcahment;fileName=Time-Table.pdf");
			trainService.generateTimetablePdfReportAllData(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-excel-timetable")
	public void showAllExcelReport(HttpServletResponse res){
		try {
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attcahment;fileName=Time-Table.xls");
			trainService.generateTimetableExcelReportAllData(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-trains")
	public ResponseEntity<?> getALLTrainsInfo(){
		try {
			List<TrainDetails> list=trainService.showAllTrains();
			return new ResponseEntity<List<TrainDetails>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchTrainByInputs(@RequestBody SearchInputs inputs){
		try {
			List<SearchResults> list=trainService.showTrainsByFilters(inputs);
			return new ResponseEntity<List<SearchResults>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
