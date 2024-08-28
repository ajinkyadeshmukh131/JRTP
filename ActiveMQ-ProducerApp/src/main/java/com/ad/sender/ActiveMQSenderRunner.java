package com.ad.sender;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRunner implements CommandLineRunner {

	@Autowired
	private JmsTemplate template;
	
	@Override
	public void run(String... args) throws Exception {
		//using lambda style inner class
//		template.send("testmq1",ses->{
//			return ses.createTextMessage("From sender::"+new Date());
//		});

		//using latest lambda style inline inner class
		template.send("testmq1",ses->ses.createTextMessage("From Sender::"+new Date()));
		System.out.println("Message is sent by sender::");
	}

}
