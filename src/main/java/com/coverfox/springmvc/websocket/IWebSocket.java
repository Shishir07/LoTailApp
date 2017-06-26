package com.coverfox.springmvc.websocket;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


public interface IWebSocket {
	@OnOpen
	public void onOpen(Session session);

	@OnMessage
	public void handleMessage(String requestMessage) throws FileNotFoundException, IOException, InterruptedException;

	@OnClose
	public void onClose(Session session);

	@OnError
	public void handleError(Session session, Throwable throwable);
	
	public void sendMessage(Object data);
}




