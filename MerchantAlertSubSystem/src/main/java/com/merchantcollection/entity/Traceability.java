package com.merchantcollection.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class Traceability {

		@CreationTimestamp
	 	@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_date", nullable = false)
	    private Date created;

	 	@UpdateTimestamp
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "modified_date", nullable = false)
	    private Date updated;

	    
	    @PrePersist
	    protected void onCreate() {
	    	updated = created = new Date();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	    	updated = new Date();
	    }

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}

		public Date getUpdated() {
			return updated;
		}

		public void setUpdated(Date updated) {
			this.updated = updated;
		}
}