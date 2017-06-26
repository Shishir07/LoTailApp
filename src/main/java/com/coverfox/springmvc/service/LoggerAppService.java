package com.coverfox.springmvc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.coverfox.springmvc.model.LogDetail;
import com.coverfox.springmvc.model.LogRecord;



public interface LoggerAppService {
	
	LogRecord findById(long id);
	
	LogRecord findByName(String name);

	List<LogRecord> findAllLogs(); 
	
	List<LogDetail> getLogDetails(String name) throws FileNotFoundException, IOException; 
	
	//public boolean doExist(User user);
	
}
