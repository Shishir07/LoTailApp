package com.coverfox.springmvc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.coverfox.springmvc.model.LogDetail;
import com.coverfox.springmvc.model.LogRecord;

@Service("loggerService")
public class LoggerAppServiceImpl implements LoggerAppService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<LogRecord> logRecords;
	
	static{
		logRecords= populateDummyRecords();
	}

	public List<LogRecord> findAllLogs() {
		return logRecords;
	}
	
	@Override
	public List<LogDetail> getLogDetails(String name) throws FileNotFoundException, IOException {
		File folder = new File("../logs");
		File[] listOfFiles = folder.listFiles();
		List<LogDetail> records = new ArrayList<LogDetail>();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String fileName = file.getName();
		    	fileName = fileName.replace(".","_");
		    	if(fileName.equals(name))
		    	try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    		int recordCounter=0;
		    	    for(String line; (line = br.readLine()) != null; ) {
		    	    	recordCounter++;
		    	    	LogDetail detail= new LogDetail(recordCounter,line);
		    	    	records.add(detail);
		    	    }
		    	}
		    }
		}
		return records;
	}
	
	
	private static List<LogRecord> populateDummyRecords(){
		File folder = new File("../logs");
		File[] listOfFiles = folder.listFiles();
		List<LogRecord> records = new ArrayList<LogRecord>();
		int nameIndex = 0;
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	nameIndex++;
		    	String fileName=file.getName();
		        //System.out.println(file.getName());
		        records.add(new LogRecord(nameIndex,fileName));
		        
		    }
		}
		return records;
	}

	@Override
	public LogRecord findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogRecord findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
