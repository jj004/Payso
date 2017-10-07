package com.merchantcollection.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TXN_DETAILS")
public class TransactionDetails extends Traceability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="terminal_id")
	private MerchantTerminal merchantTerminal;
	
	private String amount;
	@Column(name="transaction_ref_num")
	private String txnReferenceNum;
	
	@Column(name="customer_identifier")
	private String payerVpa;
	
	private String status;
	private String remarks;	
	
	@Column(length=1000000)
	private String bankInput;
	
	private String bankName;
	private String issuerName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MerchantTerminal getMerchantTerminal() {
		return merchantTerminal;
	}
	public void setMerchantTerminal(MerchantTerminal merchantTerminal) {
		this.merchantTerminal = merchantTerminal;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTxnReferenceNum() {
		return txnReferenceNum;
	}
	public void setTxnReferenceNum(String txnReferenceNum) {
		this.txnReferenceNum = txnReferenceNum;
	}
	public String getPayerVpa() {
		return payerVpa;
	}
	public void setPayerVpa(String payerVpa) {
		this.payerVpa = payerVpa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBankInput() {
		return bankInput;
	}
	public void setBankInput(String bankInput) {
		this.bankInput = bankInput;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionDetails [id=" + id + ", merchantTerminal=" + merchantTerminal + ", amount=" + amount
				+ ", txnReferenceNum=" + txnReferenceNum + ", payerVpa=" + payerVpa + ", status=" + status
				+ ", remarks=" + remarks + ", bankInput=" + bankInput + ", bankName=" + bankName + ", issuerName="
				+ issuerName + "]";
	}
}