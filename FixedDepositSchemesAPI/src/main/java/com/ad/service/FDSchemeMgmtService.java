package com.ad.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.entity.FixedDepositScheme;
import com.ad.entity.SchemeCategory;
import com.ad.repository.IFDSchemeCategoryRepo;
import com.ad.repository.IFDSchemeRepo;

@Service
public class FDSchemeMgmtService implements IFDSchemeServiceRepo {
	@Autowired
private IFDSchemeRepo fdrepo;
	@Autowired
	private IFDSchemeCategoryRepo categoryrepo;
	@Override
	public String registerNewFDScheme(FixedDepositScheme scheme) {
		FixedDepositScheme fd=fdrepo.save(scheme);
		 if (fd.getSchemeId()!=null) {
			return "FD scheme is registered with the id value::"+fd.getSchemeId();
		}
		 else {
			 return "Problem occured in saving new scheme";
		 }
	}

	@Override
	public Map<Integer, String> getFDSchemeCategory() {
		List<SchemeCategory>list=categoryrepo.findAll();
		Map<Integer, String> categoryMap=new HashMap<Integer, String>();
		list.forEach(category->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		return categoryMap;
	}

	@Override
	public List<FixedDepositScheme> showAllFDSchemes() {
	return fdrepo.findAll();
	}

	@Override
	public FixedDepositScheme showFDSchemeById(Integer schemeId) {
		return fdrepo.findById(schemeId).orElseThrow(()->new IllegalArgumentException("Scheme is not found"));
		}

	@Override
	public String upadteFDSchemeDetails(FixedDepositScheme scheme) {
		Optional<FixedDepositScheme>opt=fdrepo.findById(scheme.getSchemeId());
		if (opt.isPresent()) {
			fdrepo.save(scheme);
			return scheme.getSchemeId()+"Scheme is Updated with new info";
		} else {
            return scheme.getSchemeId()+"Scheme is not found";
		}
	}

	@Override
	public String deleteFDScheme(Integer schemeId) {
		Optional<FixedDepositScheme>opt=fdrepo.findById(schemeId);
		if (opt.isPresent()) {
			fdrepo.deleteById(schemeId);
			return schemeId+"Scheme is Deleted";
			
		} else {
             return schemeId+"Scheme is not found for deletion";
		}
	}

	@Override
	public String changeFDSchemeStatus(Integer schemeId, String status) {
		Optional<FixedDepositScheme>opt=fdrepo.findById(schemeId);
		if (opt.isPresent()) {
			FixedDepositScheme fds=opt.get();
			fds.setActive_SW(status);
			fdrepo.save(fds);
			return schemeId+"Scheme's status is updated.";
		} else {
          return schemeId+"Scheme's status is not changed";
		}
		
	}

}
