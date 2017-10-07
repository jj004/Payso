package com.merchantcollection.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MST_MERCHANT_CATEGORY")
public class MerchantCategory extends Traceability{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long merCategoryId;
	
	private String merCategory;
	private String merSubCategory;
	private String description;

	@OneToMany(mappedBy = "merCategory", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<MerchantTerminal> listMerTerminal = new ArrayList<MerchantTerminal>();

	public long getMerCategoryId() {
		return merCategoryId;
	}

	public void setMerCategoryId(long merCategoryId) {
		this.merCategoryId = merCategoryId;
	}

	public String getMerCategory() {
		return merCategory;
	}

	public void setMerCategory(String merCategory) {
		this.merCategory = merCategory;
	}

	public String getMerSubCategory() {
		return merSubCategory;
	}

	public void setMerSubCategory(String merSubCategory) {
		this.merSubCategory = merSubCategory;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
