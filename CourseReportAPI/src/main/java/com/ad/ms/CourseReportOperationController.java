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

import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;
import com.ad.service.ICourseMgmtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/report/api")
public class CourseReportOperationController {

	@Autowired
	private ICourseMgmtService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<?> fetchCourseCategories(){
		try {
			Set<String> coursesInfo=courseService.showAllCourseCategories();
			return new ResponseEntity<Set<String>>(coursesInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/training-modes")
	public ResponseEntity<?> fetchTrainingModes(){
		try {
			Set<String>trainingModesInfo=courseService.showAllTrainingMode();
			return new ResponseEntity<Set<String>>(trainingModesInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/faculty")
	public ResponseEntity<?> fetchFacultiesInfo(){
		try {
			Set<String>facultiesInfo=courseService.showAllFcaulties();
			return new ResponseEntity<Set<String>>(facultiesInfo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> fetchCoursesByFilters(@RequestBody SearchInputs inputs){
		try {
			List<SearchResults> list=courseService.showCoursesByFilters(inputs);
			return new ResponseEntity<List<SearchResults>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
		
	
	@PostMapping("/pdf-report")
	public void showPdfReport(@RequestBody SearchInputs inputs, HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attcahment;fileName=courses.pdf");
			courseService.generatePdfReport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/excel-report")
	public void showExcelReport(@RequestBody SearchInputs inputs, HttpServletResponse res){
		try {
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attcahment;fileName=courses.xls");
			courseService.generateExcelReport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-pdf-report")
	public void showAllPdfReport(HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attcahment;fileName=All-courses.pdf");
			courseService.generatePdfReportAllData(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-excel-report")
	public void showAllExcelReport(HttpServletResponse res){
		try {
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attcahment;fileName=All-courses.xls");
			courseService.generateExcelReportAllData(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
