package com.ad.reciever;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageConsumer {
	@JmsListener(destination = "testmq1")
public void readMessage(String text) {
	System.out.println("Message is read by reciever:: Thanks Sender.");
}
	
}
