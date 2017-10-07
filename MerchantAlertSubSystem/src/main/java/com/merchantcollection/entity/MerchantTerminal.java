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
@Table(name="MERCHANT_TERMINAL")
public class MerchantTerminal extends Traceability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(name="terminal_id", columnDefinition="VARCHAR(25)", unique=true)
	private String terminalId;
	
	private String terminalName;
	private String mPan;
	private String vPan;
	private String rPan;
	
	@NotNull
	@Column(unique=true)
	private String vpa;
	@Column(name="payso_qr")
	private String strPaysoQr;
	@Column(name="bharat_qr")
	private String strBharatQr;
	@Column(name="upi_qr")
	private String strUpiQr;
		
	@OneToMany(mappedBy = "merchantTerminal", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantAccount> listMerAccount = new ArrayList<MerchantAccount>();
	
	@OneToMany(mappedBy = "merchantTerminal", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantContactDetails> listMerContact = new ArrayList<MerchantContactDetails>();
	
	@OneToMany(mappedBy = "merchantTerminal", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<TransactionDetails> listTxnDetails = new ArrayList<TransactionDetails>();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="merchant_id")
	private Merchant merchant;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "merchant_status_id")
	private MerchantStatus merStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="merchant_category_id")
	private MerchantCategory merCategory;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminal_id) {
		this.terminalId = terminal_id;
	}
	
	public String getmPan() {
		return mPan;
	}

	public void setmPan(String mPan) {
		this.mPan = mPan;
	}

	public String getvPan() {
		return vPan;
	}

	public void setvPan(String vPan) {
		this.vPan = vPan;
	}

	public String getrPan() {
		return rPan;
	}

	public void setrPan(String rPan) {
		this.rPan = rPan;
	}

	public String getVpa() {
		return vpa;
	}

	public void setVpa(String vpa) {
		this.vpa = vpa;
	}

	public String getStrPaysoQr() {
		return strPaysoQr;
	}

	public void setStrPaysoQr(String strPaysoQr) {
		this.strPaysoQr = strPaysoQr;
	}

	public String getStrBharatQr() {
		return strBharatQr;
	}

	public void setStrBharatQr(String strBharatQr) {
		this.strBharatQr = strBharatQr;
	}

	public String getStrUpiQr() {
		return strUpiQr;
	}

	public void setStrUpiQr(String strUpiQr) {
		this.strUpiQr = strUpiQr;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
}