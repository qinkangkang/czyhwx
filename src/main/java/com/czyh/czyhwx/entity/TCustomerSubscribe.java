package com.czyh.czyhwx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TCustomerSubscribe entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_customer_subscribe")
public class TCustomerSubscribe extends UuidEntity {

	private static final long serialVersionUID = 1L;
	private String fcustomerId;
	private String foperationId;
	private Date foperationTime;
	private String fgps;
	private Integer frisk;
	private Integer ftype;

	// Constructors

	/** default constructor */
	public TCustomerSubscribe() {
	}

	/** minimal constructor */
	public TCustomerSubscribe(String id) {
		this.id = id;
	}

	@Column(name = "fCustomerID", length = 36)
	public String getFcustomerId() {
		return this.fcustomerId;
	}

	public void setFcustomerId(String fcustomerId) {
		this.fcustomerId = fcustomerId;
	}

	@Column(name = "fOperationID", length = 36)
	public String getFoperationId() {
		return this.foperationId;
	}

	public void setFoperationId(String foperationId) {
		this.foperationId = foperationId;
	}

	@Column(name = "fOperationTime", length = 19)
	public Date getFoperationTime() {
		return this.foperationTime;
	}

	public void setFoperationTime(Date foperationTime) {
		this.foperationTime = foperationTime;
	}

	@Column(name = "fType")
	public Integer getFtype() {
		return this.ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	@Column(name = "fGps")
	public String getFgps() {
		return fgps;
	}

	public void setFgps(String fgps) {
		this.fgps = fgps;
	}

	@Column(name = "fRisk")
	public Integer getFrisk() {
		return frisk;
	}

	public void setFrisk(Integer frisk) {
		this.frisk = frisk;
	}

}