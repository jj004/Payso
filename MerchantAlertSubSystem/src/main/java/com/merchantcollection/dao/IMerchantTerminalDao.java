package com.merchantcollection.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.merchantcollection.entity.MerchantTerminal;


public interface IMerchantTerminalDao extends CrudRepository<MerchantTerminal, Serializable>{
	//public List<MerchantTerminal> findByVpa(String mrechantIdentifier);
	
	@Query("select mt from MerchantTerminal mt where mt.vpa = ?#{[0]} or mt.terminalId = ?#{[0]}")
	public List<MerchantTerminal> findByVpaOrTerminalId(String mrechantIdentifier);
	
	@Query("select mt from MerchantTerminal mt where mt.mPan = ?#{[0]} or mt.rPan = ?#{[0]} or mt.vPan = ?#{[0]}")
	public List<MerchantTerminal> findByMpanOrVpanOrRpan(String mrechantIdentifier);
}