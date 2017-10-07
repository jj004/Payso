package com.merchantcollection.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.merchantcollection.entity.TransactionDetails;

public interface UPIDao  extends CrudRepository<TransactionDetails, Serializable>{
	
}