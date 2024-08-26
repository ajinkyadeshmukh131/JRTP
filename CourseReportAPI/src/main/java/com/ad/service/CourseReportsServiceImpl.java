package com.ad.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ad.entity.CourseDetails;
import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;
import com.ad.repository.ICourseDetailsRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service("courseService")
public class CourseReportsServiceImpl implements ICourseMgmtService {
	@Autowired
	private ICourseDetailsRepository courseRepo;

	@Override
	public Set<String> showAllCourseCategories() {
		return courseRepo.getUniqueCourseCategories();
	}

	@Override
	public Set<String> showAllTrainingMode() {
		return courseRepo.getUniqueTrainingModes();
	}

	@Override
	public Set<String> showAllFcaulties() {
		return courseRepo.getUniqueFacultyNames();
	}

	@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		CourseDetails entity=new CourseDetails();
		String category=inputs.getCourseCategory();
		if(StringUtils.hasLength(category))
			entity.setCourseCategory(category);
		
		String facultyName=inputs.getFacultyName();
		if(StringUtils.hasLength(facultyName))
			entity.setFacultyName(facultyName);
		
		String TrainingMode=inputs.getTrainingMode();
		if(StringUtils.hasLength(TrainingMode));
		entity.setTrainingMode(TrainingMode);
		
		LocalDateTime startDate=inputs.getStartDate();
		if(ObjectUtils.isEmpty(startDate))
			entity.setStartDate(startDate);
		
		Example<CourseDetails> example=Example.of(entity);
		
		List<CourseDetails> listEntities=courseRepo.findAll(example);
		List<SearchResults> listResults=new ArrayList();
		listEntities.forEach(course->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
	return listResults;
	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults> listResults=showCoursesByFilters(inputs);
		
		Document document=new Document();
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(18);
		font.setColor(Color.BLACK);
		
		Paragraph para=new Paragraph("Search Report of Courses",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		
	PdfPTable table=new PdfPTable(10);
	table.setWidthPercentage(70);
	table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
	table.setSpacingBefore(1.0f);
	
	PdfPCell cell=new PdfPCell();
	cell.setBackgroundColor(Color.WHITE);
	cell.setPadding(10);
	Font cellFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
	cellFont.setColor(Color.BLACK);

	cell.setPhrase(new Phrase("CourseId",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("CourseName",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Category",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("FacultyName",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Location",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Fees",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("CourseStatus",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("TrainingMode",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("AdminContact",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("StartDate",cellFont));
	table.addCell(cell);
	
	listResults.forEach(result->{
		table.addCell(String.valueOf(result.getCourseId()));
		table.addCell(result.getCourseName());
		table.addCell(result.getCourseCategory());
		table.addCell(result.getFacultyName());
		table.addCell(result.getLocation());
		table.addCell(String.valueOf(result.getFee()));
		table.addCell(result.getCourseStatus());
		table.addCell(result.getTrainingMode());
		table.addCell(String.valueOf(result.getAdminContact()));
		table.addCell(result.getStartDate().toString());
	});
	document.add(table);
	document.close();
	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults>listResults=showCoursesByFilters(inputs);
		
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseId");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Category");
		headerRow.createCell(3).setCellValue("FacultyName");
		headerRow.createCell(4).setCellValue("Location");
		headerRow.createCell(5).setCellValue("Fees");
		headerRow.createCell(6).setCellValue("CourseStatus");
		headerRow.createCell(7).setCellValue("TrainingMode");
		headerRow.createCell(8).setCellValue("AdminContact");
		headerRow.createCell(9).setCellValue("StartDate");
		
		int i=1;
		for(SearchResults result:listResults) {
			HSSFRow dataRow=sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourseId());
			dataRow.createCell(1).setCellValue(result.getCourseName());
			dataRow.createCell(2).setCellValue(result.getLocation());
			dataRow.createCell(3).setCellValue(result.getCourseCategory());
			dataRow.createCell(4).setCellValue(result.getFacultyName());
			dataRow.createCell(5).setCellValue(result.getFee());
			dataRow.createCell(6).setCellValue(result.getAdminContact());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getStartDate());
			dataRow.createCell(9).setCellValue(result.getCourseStatus());
			i++;
			
		}
         ServletOutputStream outputstream=res.getOutputStream();
         workbook.write(outputstream);
         outputstream.close();
         workbook.close();
	}

	@Override
	public void generatePdfReportAllData(HttpServletResponse res)throws Exception {
		List<CourseDetails> list=courseRepo.findAll();
		
		List<SearchResults> listResults=new ArrayList();
		list.forEach(course->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(course,result);
			listResults.add(result);
		});
		Document document=new Document();
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(25);
		font.setColor(Color.BLACK);
		
		Paragraph para=new Paragraph("Search Report of Courses",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		
	PdfPTable table=new PdfPTable(10);
	table.setWidthPercentage(110);
	table.setWidths(new float[] {3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f});
	table.setSpacingBefore(15.0f);
	
	PdfPCell cell=new PdfPCell();
	cell.setBackgroundColor(Color.WHITE);
	cell.setPadding(5);
	Font cellFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
	cellFont.setColor(Color.BLACK);

	cell.setPhrase(new Phrase("Course Id",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Course Name",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Category",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Faculty Name",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Location",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Fees",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Course Status",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Training Mode",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Admin Contact",cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Start Date",cellFont));
	table.addCell(cell);
	
	listResults.forEach(result->{
		table.addCell(String.valueOf(result.getCourseId()));
		table.addCell(result.getCourseName());
		table.addCell(result.getCourseCategory());
		table.addCell(result.getFacultyName());
		table.addCell(result.getLocation());
		table.addCell(String.valueOf(result.getFee()));
		table.addCell(result.getCourseStatus());
		table.addCell(result.getTrainingMode());
		table.addCell(String.valueOf(result.getAdminContact()));
		table.addCell(result.getStartDate().toString());
	});
	document.add(table);
	document.close();
		
	}

	@Override
	public void generateExcelReportAllData(HttpServletResponse res) throws Exception {
		List<CourseDetails> list=courseRepo.findAll();
		List<SearchResults> listResults=new ArrayList();
		list.forEach(course->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
         HSSFWorkbook workbook=new HSSFWorkbook();
		
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseId");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Category");
		headerRow.createCell(3).setCellValue("FacultyName");
		headerRow.createCell(4).setCellValue("Location");
		headerRow.createCell(5).setCellValue("Fees");
		headerRow.createCell(6).setCellValue("CourseStatus");
		headerRow.createCell(7).setCellValue("TrainingMode");
		headerRow.createCell(8).setCellValue("AdminContact");
		headerRow.createCell(9).setCellValue("StartDate");
		
		int i=1;
		for(SearchResults result:listResults) {
			HSSFRow dataRow=sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourseId());
			dataRow.createCell(1).setCellValue(result.getCourseName());
			dataRow.createCell(2).setCellValue(result.getLocation());
			dataRow.createCell(3).setCellValue(result.getCourseCategory());
			dataRow.createCell(4).setCellValue(result.getFacultyName());
			dataRow.createCell(5).setCellValue(result.getFee());
			dataRow.createCell(6).setCellValue(result.getAdminContact());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getStartDate());
			dataRow.createCell(9).setCellValue(result.getCourseStatus());
			i++;
			
		}
        ServletOutputStream outputStream=res.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        
	}
}
