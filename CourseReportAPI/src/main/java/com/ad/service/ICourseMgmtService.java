package com.ad.service;

import java.util.List;
import java.util.Set;

import javax.naming.directory.SearchResult;

import com.ad.model.SearchInputs;
import com.ad.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseMgmtService {
    public Set<String> showAllCourseCategories();
    public Set<String> showAllTrainingMode();
    public Set<String> showAllFcaulties();
    
    public List<SearchResults> showCoursesByFilters(SearchInputs inputs);
    
    public void generatePdfReport(SearchInputs inputs, HttpServletResponse res)throws Exception;
    public void generateExcelReport(SearchInputs inputs, HttpServletResponse res)throws Exception;
    
    public void generatePdfReportAllData(HttpServletResponse res)throws Exception;
    public void generateExcelReportAllData(HttpServletResponse res) throws Exception;
	
}
