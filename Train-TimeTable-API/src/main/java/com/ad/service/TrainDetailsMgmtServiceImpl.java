package com.ad.service;

import java.awt.Color;
import java.io.IOException;
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
import org.springframework.util.StringUtils;

import com.ad.entity.TrainDetails;
import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;
import com.ad.repository.ITrainDetailsRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service("trainService")
public class TrainDetailsMgmtServiceImpl implements ITrainDetailsMgmtService {
	@Autowired
	private ITrainDetailsRepository trainRepo;

	@Override
	public Set<String> showAllTrainCategories() {
		return trainRepo.getUniqueTrainCategories();
	}

	@Override
	public Set<String> showAllSourceStation() {
		return trainRepo.getUniqueSourceStation();
	}

	@Override
	public Set<String> showAllDestinationStation() {
		return trainRepo.getUniqueDestinationStation();
	}

	@Override
	public Set<String> showAllActiveDays() {
		return trainRepo.getUniqueActiveDays();
	}

	@Override
	public List<SearchResults> showTrainsByFilters(SearchInputs inputs) {
			TrainDetails entity=new TrainDetails();
			String category=inputs.getTrainCategory();
			if(StringUtils.hasLength(category))
				entity.setCategory(category);
			
			String activeDays=inputs.getActiveDays();
			if(StringUtils.hasLength(activeDays))
				entity.setActiveDays(activeDays);
			
			String sourceStation=inputs.getSourceStation();
			if(StringUtils.hasLength(sourceStation));
			entity.setSourceStation(sourceStation);
			
			String destinationStation=inputs.getDestinationStation();
			if(StringUtils.hasLength(destinationStation));
			entity.setSourceStation(destinationStation);
			
			Example<TrainDetails> example=Example.of(entity);
			
			List<TrainDetails> listEntities=trainRepo.findAll(example);
			List<SearchResults> listResults=new ArrayList();
			listEntities.forEach(train->{
				SearchResults result=new SearchResults();
				BeanUtils.copyProperties(train, result);
				listResults.add(result);
			});
		return listResults;
		}	
	

	@Override
	public void generateTimetablePdfReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults> listResults=showTrainsByFilters(inputs);
		
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(10);
		font.setColor(Color.BLACK);
		
		Paragraph para=new Paragraph("Search Result of Train Info",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		
		PdfPTable table=new PdfPTable(9);
		table.setWidthPercentage(110);
		table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		table.setSpacingBefore(1.0f);
		
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(10);
		Font cellFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		cellFont.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Train No.",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Train Name",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Source Station",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Destination Station",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Source StationTime",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Destination Station Time",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Active Days",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Train Status",cellFont));
		table.addCell(cell);
		
		listResults.forEach(result->{
			table.addCell(String.valueOf(result.getTrainNo()));
			table.addCell(result.getTrainName());
			table.addCell(result.getCategory());
			table.addCell(result.getSourceStation());
			table.addCell(result.getDestinationStation());
			table.addCell(String.valueOf(result.getSourceStationTime()));
			table.addCell(String.valueOf(result.getDestinationStationTime()));
			
			table.addCell(result.getActiveDays());
			table.addCell(result.getTrainStatus());
			
		});
		document.add(table);
		document.close();
}
		
	

	@Override
	public void generateTimetableExcelReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults>listResults=showTrainsByFilters(inputs);
		
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		HSSFSheet sheet1=workbook.createSheet("Train Details");
		
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("Train No.");
		headerRow.createCell(1).setCellValue("Train Name");
		headerRow.createCell(2).setCellValue("Category");
		headerRow.createCell(3).setCellValue("Source Station");
		headerRow.createCell(4).setCellValue("Destination Station");
		headerRow.createCell(5).setCellValue("Source StationTime");
		headerRow.createCell(6).setCellValue("Destination Station Time");
		headerRow.createCell(7).setCellValue("Active Days");
		headerRow.createCell(8).setCellValue("Train Status");
		
		
		int i=1;
		for(SearchResults result:listResults) {
			HSSFRow dataRow=sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(result.getTrainNo());
			dataRow.createCell(1).setCellValue(result.getTrainName());
			dataRow.createCell(2).setCellValue(result.getCategory());
			dataRow.createCell(3).setCellValue(result.getSourceStation());
			dataRow.createCell(4).setCellValue(result.getDestinationStation());
			dataRow.createCell(5).setCellValue((String.valueOf(result.getSourceStationTime())));
			dataRow.createCell(6).setCellValue((String.valueOf(result.getDestinationStationTime())));
			dataRow.createCell(7).setCellValue(result.getActiveDays());
			dataRow.createCell(8).setCellValue(result.getTrainStatus());
			
			i++;
			
		}
         ServletOutputStream outputstream=res.getOutputStream();
         workbook.write(outputstream);
         outputstream.close();
         workbook.close();
	}
		
	

	@Override
	public void generateTimetablePdfReportAllData(HttpServletResponse res) throws  Exception {
		List<TrainDetails> list=trainRepo.findAll();
		List<SearchResults> listResults=new ArrayList();
		list.forEach(train->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(train, result);
			listResults.add(result);
		});
		Document document=new Document();
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(10);
		font.setColor(Color.BLACK);
		
		Paragraph para=new Paragraph("Search Result of Train Info",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		
		PdfPTable table=new PdfPTable(9);
		table.setWidthPercentage(110);
		table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		table.setSpacingBefore(15.0f);
		
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(10);
		Font cellFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		cellFont.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Train No.",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Train Name",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Source Station",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Destination Station",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Source Station Time",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Destination Station Time",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Active Days",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Train Status",cellFont));
		table.addCell(cell);
		
		listResults.forEach(result->{
			
				table.addCell(String.valueOf(result.getTrainNo()));
				table.addCell(result.getTrainName());
				table.addCell(result.getCategory());
				table.addCell(result.getSourceStation());
				table.addCell(result.getDestinationStation());
				table.addCell(String.valueOf(result.getSourceStationTime()));
				table.addCell(String.valueOf(result.getDestinationStationTime()));
				table.addCell(result.getActiveDays());
				table.addCell(result.getTrainStatus());
			
		});
		document.add(table);
		document.close();
		}
	

	@Override
	public void generateTimetableExcelReportAllData(HttpServletResponse res) throws Exception {
			List<TrainDetails> list=trainRepo.findAll();
			List<SearchResults> listResults=new ArrayList();
			list.forEach(train->{
				SearchResults result=new SearchResults();
				BeanUtils.copyProperties(train, result);
				listResults.add(result);
			});
			
			HSSFWorkbook workbook=new HSSFWorkbook();
			
			HSSFSheet sheet1=workbook.createSheet("Train Details");
			
			HSSFRow headerRow=sheet1.createRow(0);
			headerRow.createCell(0).setCellValue("Train No.");
			headerRow.createCell(1).setCellValue("Train Name");
			headerRow.createCell(2).setCellValue("Category");
			headerRow.createCell(3).setCellValue("Source Station");
			headerRow.createCell(4).setCellValue("Destination Station");
			headerRow.createCell(5).setCellValue("Source StationTime");
			headerRow.createCell(6).setCellValue("Destination Station Time");
			headerRow.createCell(7).setCellValue("Active Days");
			headerRow.createCell(8).setCellValue("Train Status");
			
			
			int i=1;
			for(SearchResults result:listResults) {
				HSSFRow dataRow=sheet1.createRow(i);
				dataRow.createCell(0).setCellValue(String.valueOf(result.getTrainNo()));
				dataRow.createCell(1).setCellValue(result.getTrainName());
				dataRow.createCell(2).setCellValue(result.getCategory());
				dataRow.createCell(3).setCellValue(result.getSourceStation());
				dataRow.createCell(4).setCellValue(result.getDestinationStation());
				dataRow.createCell(5).setCellValue((String.valueOf(result.getSourceStationTime())));
				dataRow.createCell(6).setCellValue((String.valueOf(result.getDestinationStationTime())));
				dataRow.createCell(7).setCellValue(result.getActiveDays());
				dataRow.createCell(8).setCellValue(result.getTrainStatus());
				
				i++;
				
			}
	         ServletOutputStream outputstream=res.getOutputStream();
	         workbook.write(outputstream);
	         outputstream.close();
	         workbook.close();
	}

	@Override
	public List<TrainDetails> showAllTrains() {
		
		return trainRepo.findAll();
	}

}
