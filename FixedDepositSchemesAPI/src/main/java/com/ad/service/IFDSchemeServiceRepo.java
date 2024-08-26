package com.ad.service;

import java.util.List;
import java.util.Map;

import com.ad.entity.FixedDepositScheme;

public interface IFDSchemeServiceRepo {
  public String registerNewFDScheme(FixedDepositScheme scheme);
  public Map<Integer, String> getFDSchemeCategory();
  public List<FixedDepositScheme>showAllFDSchemes();
  public FixedDepositScheme showFDSchemeById(Integer schemeId);
  public String upadteFDSchemeDetails(FixedDepositScheme scheme);
  public String deleteFDScheme(Integer schemeId);
  public String changeFDSchemeStatus(Integer schemeId, String status);
}
