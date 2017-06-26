package com.coverfox.springmvc.websocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.coverfox.springmvc.model.LogDetail;
import com.coverfox.springmvc.model.LogRecord;
import com.mylogger.Mylogger;


@ServerEndpoint("/handleServerSocket")
public class WebSocketHandler extends WebSocket{

	@OnMessage
	public void handleMessage(String requestMessage) throws FileNotFoundException, IOException, InterruptedException {
		String name = requestMessage;
		name = name.replace("_",".");
		File myFile= new File("../logs/"+name);
		long length = myFile.length();
		for(int i=0;i<500;i++){
			if(i==0) firstTimeLoad(length,name);
			Logger logger = Mylogger.logger;
			double rndm = Math.random();
	        logger.info("Appended in currently viewing file"+rndm);
	        synchronized(this){
	            wait(2000);
	            length = callForAddedLogs(length,name);
	        }
	        
		}
	
	}

	private void firstTimeLoad(long length, String name) throws FileNotFoundException, IOException {
		File myFile= new File("../logs/"+name);
		JSONArray records = new JSONArray();
		if(myFile.isFile()){
			createRecordsToSend(records,myFile);
		
		}
		
	}

	private long callForAddedLogs(long length,String name) throws FileNotFoundException, IOException {
		File myFile= new File("../logs/"+name);
		JSONArray records = new JSONArray();
		long newLength = myFile.length();
		if(newLength!=length && myFile.isFile()){
			createRecordsToSend(records,myFile);
		}
		return newLength;
	}
	
	private void createRecordsToSend(JSONArray records, File myFile) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(myFile))) {
    		int recordCounter=0;
    	    for(String line; (line = br.readLine()) != null; ) {
    	    	recordCounter++;
    	    	JSONObject obj = new JSONObject();
    	    	obj.put("id",recordCounter);
    	    	obj.put("detail", line);
    	    	records.put(obj);
    	    }
	    	
	    }
		this.sendMessage(records.toString());
		
	}

	@Override
	public void sendMessage(Object data) {
		super.sendToCurrentSession(data);
	}

}
