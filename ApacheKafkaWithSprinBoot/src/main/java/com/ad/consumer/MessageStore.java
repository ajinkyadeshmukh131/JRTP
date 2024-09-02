package com.ad.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageStore {

	private List<String> listMsgs=new ArrayList();
	
	public void addMessage(String message) {
		listMsgs.add(message);
	}
	
	public String getMessage() {
		return listMsgs.toString();
	}
	
	
}
