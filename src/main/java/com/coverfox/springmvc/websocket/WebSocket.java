package com.coverfox.springmvc.websocket;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.json.JSONObject;
import org.apache.log4j.Logger;

import com.mylogger.Mylogger;

public abstract class WebSocket implements IWebSocket {
	protected Session session;
	Logger logger = Mylogger.logger;
	//private static final String OPEN_SOCKET_MESSAGE = "Socket connection established";
	private static final String STATUS = "status";
	private static final String SOCKET_ERROR = "socketError";
	private static final String RESPONSE_MESSAGE = "msg";
	
	@OnOpen
	@Override
	public final void onOpen(Session session) {
		if (session != null && session.isOpen()) {
			this.session = session;
			//session.getBasicRemote().sendText(OPEN_SOCKET_MESSAGE);
			if (logger.isDebugEnabled())
				logger.debug("Socket connection details :: Port : " + session.getRequestURI().getPort()	+ " IpAddress : " + session.getRequestURI().getHost());
		}
	}
	
	
	@OnMessage
	public abstract void handleMessage(String requestMessage) throws FileNotFoundException, IOException, InterruptedException;


	@Override
	public abstract void sendMessage(Object data);


	@OnClose
	@Override
	public final void onClose(Session session) {
		try {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		} catch (IOException e) {
			String errorMessage = "Error while closing web socket session - " + e.getMessage();
			logger.error(errorMessage, e);
			throw new RuntimeException(errorMessage, e);
		}

	}
	
	/**
	 * Handles all not handled Exceptions in send message and handle message methods.
	 * @see com.eka.websocket.WebSocket#handleMessage(String requestMessage)
	 * @see com.eka.websocket.WebSocket#sendMessage(Object data)
	 */
	@OnError
	@Override
	public final void handleError(Session session, Throwable throwable) {
		JSONObject resp = new JSONObject();
		resp.put(STATUS, SOCKET_ERROR);
		resp.put(RESPONSE_MESSAGE, "Error while creating wesocket session : ");
		logger.error(throwable.getMessage(), throwable);
		session.getAsyncRemote().sendText(resp.toString());
	}

	/**
	 * Send messages to the current open session through web socket.
	 * @param data
	 */
	public final void sendToCurrentSession(Object data) {
		if (data instanceof String) {
			session.getAsyncRemote().sendText(String.valueOf(data));
		} else {
			session.getAsyncRemote().sendObject(data);
		}
	}
	
	/**
	 * Sends message through all the open web socket sessions.
	 * @param data
	 * @throws IOException
	 */
	public final void sendToAllOpenSessions(Object data) throws IOException {
		if (this.session != null && data != null)
			this.session.getOpenSessions().forEach(s -> {
				if (data instanceof String) {
					s.getAsyncRemote().sendText(String.valueOf(data));
				} else {
					s.getAsyncRemote().sendObject(data);
				}
			});
	}
	
}
