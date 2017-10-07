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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MST_MERCHANT_STATUS")
public class MerchantStatus extends Traceability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="merchant_status_id")
	private long merStatusId;
	@Column(name="merchant_status", columnDefinition="VARCHAR(15)")
	private String merStatus;
	private String description;
	
	@OneToMany(mappedBy = "merStatus", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Merchant> listMerchant = new ArrayList<Merchant>();
	
	@OneToMany(mappedBy = "merStatus", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantTerminal> listMerchantTerminal = new ArrayList<MerchantTerminal>();

	public long getMerStatusId() {
		return merStatusId;
	}

	public void setMerStatusId(long merStatusId) {
		this.merStatusId = merStatusId;
	}

	public String getMerStatus() {
		return merStatus;
	}

	public void setMerStatus(String merStatus) {
		this.merStatus = merStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
