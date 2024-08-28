package com.ad.reciever;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ad.model.ActorsInfo;

@Component
public class ObjectMessageReciever {

	@JmsListener(destination = "obj_mq1")
	public void consumeMessage(ActorsInfo info) {
		System.out.println("Recieved Object Data is::"+info);
	}
}
