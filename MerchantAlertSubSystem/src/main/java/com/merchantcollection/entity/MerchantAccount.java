package com.merchantcollection.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT_ACCOUNT")
public class MerchantAccount extends Traceability{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long merchantAccId;

	private String bankName;
	
	private String bankAccountName;
	
	private long bankAccountNum;
	
	private String ifscCode;
	
	private String bankCity;
	
	@ManyToOne
	@JoinColumn(name="merchant_terminal_id")
	private MerchantTerminal merchantTerminal;

	public long getMerchantAccId() {
		return merchantAccId;
	}

	public void setMerchantAccId(long merchantAccId) {
		this.merchantAccId = merchantAccId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public long getBankAccountNum() {
		return bankAccountNum;
	}

	public void setBankAccountNum(long bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
}
