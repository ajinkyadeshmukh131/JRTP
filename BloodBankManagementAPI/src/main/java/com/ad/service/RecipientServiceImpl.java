package com.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.entity.Recipient;
import com.ad.repository.IRecipientRepository;

@Service
public class RecipientServiceImpl implements IRecipientServiceRepo {

	@Autowired
	private IRecipientRepository repo;
	
	
	@Override
	public String registerNewRecipient(Recipient reci) {
		Recipient recipient=repo.save(reci);
		if (recipient.getRecipientId()!=null) {
			return"Recipient is registered.";
		}
		return null;
	}

	@Override
	public List<Recipient> getAllRcipients() {
		
		return repo.findAll();
		
	}

	@Override
	public Recipient getRecipientById(Long id) {
		
		return repo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Id"));
	}

	@Override
	public String deleteRecipientInfo(Long id) {
		repo.deleteById(id);
		return "Recipient is deleted with id::"+id;
	}

	@Override
	public String updateRecipientInfo(Recipient recip) {
		Optional<Recipient> opt=repo.findById(recip.getRecipientId());
		if (opt.isPresent()) {
			repo.save(recip);
			return recip.getRecipientId()+"Recipient is updated with new info";
		}
		return recip.getRecipientId()+"Recipient is not found for updation";
	}

}
