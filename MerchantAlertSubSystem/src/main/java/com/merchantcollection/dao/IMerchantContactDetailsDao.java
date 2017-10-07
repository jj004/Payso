package com.merchantcollection.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.merchantcollection.entity.MerchantContactDetails;
import com.merchantcollection.entity.MerchantTerminal;

public interface IMerchantContactDetailsDao extends CrudRepository<MerchantContactDetails, Serializable>{
	
	List<MerchantContactDetails> findByMerchantTerminal(MerchantTerminal merTerminal);
}