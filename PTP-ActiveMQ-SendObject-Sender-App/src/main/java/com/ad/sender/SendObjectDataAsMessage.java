package com.ad.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ad.model.ActorsInfo;

@Component
public class SendObjectDataAsMessage {
	@Autowired
	private JmsTemplate template;
	
	@Scheduled(cron="0/20 * * * * * ")
	public void sendMessage() {
		//prepare object
		ActorsInfo info=new ActorsInfo(1001,"Ranveer Singh", "Mumbai");
		//send object as message
		template.convertAndSend("obj_mq1",info);
		//print to console
		System.out.println("Object is sent as message::");
	}

}
