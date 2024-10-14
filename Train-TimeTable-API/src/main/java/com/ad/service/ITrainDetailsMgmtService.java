package com.ad.service;

import java.util.List;
import java.util.Set;

import com.ad.entity.TrainDetails;
import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ITrainDetailsMgmtService {
    public Set<String> showAllTrainCategories();
    public Set<String> showAllSourceStation();
    public Set<String> showAllDestinationStation();
    public Set<String> showAllActiveDays();
    public List<TrainDetails>showAllTrains();
    
    public List<SearchResults> showTrainsByFilters(SearchInputs inputs);
    
    public void generateTimetablePdfReport(SearchInputs inputs, HttpServletResponse res)throws Exception;
    public void generateTimetableExcelReport(SearchInputs inputs, HttpServletResponse res)throws Exception;
    
    public void generateTimetablePdfReportAllData(HttpServletResponse res)throws Exception;
    public void generateTimetableExcelReportAllData(HttpServletResponse res) throws Exception;
	
}
