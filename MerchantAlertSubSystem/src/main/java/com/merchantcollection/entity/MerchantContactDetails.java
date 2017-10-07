package com.merchantcollection.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT_CONTACT")
public class MerchantContactDetails extends Traceability {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long merContactId;
	
	private String merEmail;
	
	private String contactPersonName;
	
	private String mobileNumber;
	
	private String officeNumber;
	
	@ManyToOne
	@JoinColumn(name="terminal_id")
	private MerchantTerminal merchantTerminal;
	
	public long getMerContactId() {
		return merContactId;
	}

	public void setMerContactId(long merContactId) {
		this.merContactId = merContactId;
	}

	public String getMerEmail() {
		return merEmail;
	}

	public void setMerEmail(String merEmail) {
		this.merEmail = merEmail;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
}
