package com.ad.service;

import java.util.List;


import com.ad.entity.Recipient;

public interface IRecipientServiceRepo {
	public String registerNewRecipient(Recipient donor);
	  public List<Recipient>getAllRcipients();
	  public  Recipient getRecipientById(Long id);
	  public String deleteRecipientInfo(Long id);
	  public String updateRecipientInfo(Recipient donor);
	  
}
