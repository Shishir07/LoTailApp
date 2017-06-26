package com.coverfox.springmvc.controller;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coverfox.springmvc.model.LogDetail;
import com.coverfox.springmvc.model.LogRecord;
import com.coverfox.springmvc.service.LoggerAppService;
import com.mylogger.Mylogger;




@RestController
public class LoggerAppRestController {
	@Autowired
	LoggerAppService loggerService;
	
	@RequestMapping(value = "/logs/", method = RequestMethod.GET)
    public ResponseEntity<List<LogRecord>> listAllLogs() {
        List<LogRecord> records = loggerService.findAllLogs();
        if(records.isEmpty()){
            return new ResponseEntity<List<LogRecord>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        Logger logger = Mylogger.logger;

        logger.info("hahahhhahahahahah");
        return new ResponseEntity<List<LogRecord>>(records, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/logDetail/{logName}", method = RequestMethod.GET)
	public ResponseEntity<List<LogDetail>> getLogDetails(@PathVariable("logName")String logName) throws FileNotFoundException, IOException {
		List<LogDetail> records = loggerService.getLogDetails(logName);
        if(records.isEmpty()){
            return new ResponseEntity<List<LogDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        Logger logger = Mylogger.logger;
        logger.info("hahahhhahahahahah");
        return new ResponseEntity<List<LogDetail>>(records, HttpStatus.OK);
	 }
}