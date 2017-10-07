package com.merchantcollection.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MERCHANT_ADDRESS")
public class MerchantAddress extends Traceability {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long merAddrId;
	
	private String address1;
	private String address2;
	private String address3;
	private String landmark;
	private String state;
	private String city;
	
	@ManyToOne
	@JoinColumn(name="merchant_id")
	private Merchant merchant;

	public long getMerAddrId() {
		return merAddrId;
	}

	public void setMerAddrId(long merAddrId) {
		this.merAddrId = merAddrId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
