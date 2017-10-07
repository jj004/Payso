package com.merchantcollection.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name="MERCHANT")
public class Merchant extends Traceability{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="merchant_id", nullable=false)
	private long merId;
	
	private String merchantName;
	@Column(name="bank_merchant_id")
	private String bankMerId;
	
	@NotNull
	@Column(unique=true)
	private long formNo;
	
	@Column(name="merchant_dob")
	private String merDob;	//Date of Birth
	@Column(name="merchant_type")
	private String merType;	//online or offline Merchant
	@Column(name="merchant_nature")
	private String merNature;	//online or offline Merchant
	@Column(name="merchant_key")
	private String merKey; //
/*	private String makerId;
	private String checkerId;*/
	
/*	private String lessThanThousOtherOffusPer;
	private String lessThanThousOtherOnusPer;
	private String grtrThanTwoThousOtherOffusPer;
	private String oneThousToTwoThousOtherOffusPer;
	private String grtrThanTwoThousOtherOnusPer;	
	private String masterCardDomesticCreditOffus;
	private String masterCardDomesticCreditOnus;
	private String masterCardInternationalCreditOnus;
	private String masterCardInternationalCreditOffus;
	private String masterCardInternationalDebitOnus;
	private String masterCardInternationalDebitOffus;
	private String visaDomesticCreditOffus;
	private String visaDomesticCreditOnus;
	private String visaInternationalCreditOnus;
	private String visaInternationalCreditOffus;
	private String rupayDomesticCreditOnus;
	private String rupayDomesticCreditOffus;*/
	

	/*@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<MerchantContactDetails> listMerContact = new ArrayList<MerchantContactDetails>();*/
    
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantTerminal> listMerchantTerminal = new ArrayList<MerchantTerminal>();
	
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantAddress> listMerAddr = new ArrayList<MerchantAddress>();
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "merchant_status_id")
	private MerchantStatus merStatus;
	
	public long getMerId(){
		return merId;
	}

	public void setMerId(long merId) {
		this.merId = merId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBankMerId() {
		return bankMerId;
	}

	public void setBankMerId(String bankMerId) {
		this.bankMerId = bankMerId;
	}

	public long getFormNo() {
		return formNo;
	}

	public void setFormNo(long formNo) {
		this.formNo = formNo;
	}
	
	public String getMerDob() {
		return merDob;
	}

	public void setMerDob(String merDob) {
		this.merDob = merDob;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getMerNature() {
		return merNature;
	}

	public void setMerNature(String merNature) {
		this.merNature = merNature;
	}
	
	public String getMerKey() {
		return merKey;
	}

	public void setMerKey(String merKey) {
		this.merKey = merKey;
	}
}