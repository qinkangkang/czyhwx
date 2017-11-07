package com.czyh.czyhwx.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_sponsor")
public class TSponsor extends UuidEntity {

	private static final long serialVersionUID = 1L;

	// Fields
	private TCustomer TCustomer;
	private String fname;
	private String ffullName;
	private String fnumber;
	private String fbrief;
	private String fdetail;
	private String fdetailHtmlUrl;
	private String fimage;
	private String fphone;
	private Integer ftype;
	private Integer flevel;
	private Integer fstatus;
	private BigDecimal fscore;
	private String fwebSite;
	private Integer fbankId;
	private String fbank;
	private String fbankAccount;
	private String fbankAccountName;
	private String fbankAccountPersonId;
	private String faddress;
	private String fgps;
	private String fcontractEffective;
	private BigDecimal frate;
	private BigDecimal fnoSettlementBalance;
	private BigDecimal ffreezeBalance;
	private BigDecimal fbalance;
	private Long fbdId;
	private Long fcreaterId;
	private Date fcreateTime;
	private Date fupdateTime;
	private Set<TEvent> TEvents = new HashSet<TEvent>(0);
	private Set<TOrder> TOrders = new HashSet<TOrder>(0);
	private Set<TOrderVerification> TOrderVerifications = new HashSet<TOrderVerification>(0);
	private Set<TSponsorWithdraw> TSponsorWithdraws = new HashSet<TSponsorWithdraw>(0);
	private Set<TSponsorBalance> TSponsorBalances = new HashSet<TSponsorBalance>(0);
	private Set<TSponsorStatement> TSponsorStatements = new HashSet<TSponsorStatement>(0);
	private Set<TMerchant> TMerchants = new HashSet<TMerchant>(0);

	// Constructors

	/** default constructor */
	public TSponsor() {
	}

	/** minimal constructor */
	public TSponsor(String id) {
		this.id = id;
	}

	// Property accessors
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fUserID")
	public TCustomer getTCustomer() {
		return this.TCustomer;
	}

	public void setTCustomer(TCustomer TCustomer) {
		this.TCustomer = TCustomer;
	}

	@Column(name = "fName")
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "fFullName")
	public String getFfullName() {
		return this.ffullName;
	}

	public void setFfullName(String ffullName) {
		this.ffullName = ffullName;
	}

	@Column(name = "fNumber", length = 32)
	public String getFnumber() {
		return this.fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	@Column(name = "fBrief", length = 2048)
	public String getFbrief() {
		return this.fbrief;
	}

	public void setFbrief(String fbrief) {
		this.fbrief = fbrief;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "fDetail", columnDefinition = "longtext")
	public String getFdetail() {
		return this.fdetail;
	}

	public void setFdetail(String fdetail) {
		this.fdetail = fdetail;
	}

	@Column(name = "fDetailHtmlUrl", length = 255)
	public String getFdetailHtmlUrl() {
		return this.fdetailHtmlUrl;
	}

	public void setFdetailHtmlUrl(String fdetailHtmlUrl) {
		this.fdetailHtmlUrl = fdetailHtmlUrl;
	}

	@Column(name = "fImage", length = 255)
	public String getFimage() {
		return this.fimage;
	}

	public void setFimage(String fimage) {
		this.fimage = fimage;
	}

	@Column(name = "fPhone")
	public String getFphone() {
		return this.fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	@Column(name = "fType")
	public Integer getFtype() {
		return this.ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	@Column(name = "fLevel")
	public Integer getFlevel() {
		return this.flevel;
	}

	public void setFlevel(Integer flevel) {
		this.flevel = flevel;
	}

	@Column(name = "fStatus")
	public Integer getFstatus() {
		return this.fstatus;
	}

	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}

	@Column(name = "fScore", precision = 8)
	public BigDecimal getFscore() {
		return this.fscore;
	}

	public void setFscore(BigDecimal fscore) {
		this.fscore = fscore;
	}

	@Column(name = "fWebSite")
	public String getFwebSite() {
		return this.fwebSite;
	}

	public void setFwebSite(String fwebSite) {
		this.fwebSite = fwebSite;
	}

	@Column(name = "fBankId")
	public Integer getFbankId() {
		return this.fbankId;
	}

	public void setFbankId(Integer fbankId) {
		this.fbankId = fbankId;
	}

	@Column(name = "fBank")
	public String getFbank() {
		return this.fbank;
	}

	public void setFbank(String fbank) {
		this.fbank = fbank;
	}

	@Column(name = "fBankAccount")
	public String getFbankAccount() {
		return this.fbankAccount;
	}

	public void setFbankAccount(String fbankAccount) {
		this.fbankAccount = fbankAccount;
	}

	@Column(name = "fBankAccountName")
	public String getFbankAccountName() {
		return this.fbankAccountName;
	}

	public void setFbankAccountName(String fbankAccountName) {
		this.fbankAccountName = fbankAccountName;
	}

	@Column(name = "fBankAccountPersonId")
	public String getFbankAccountPersonId() {
		return this.fbankAccountPersonId;
	}

	public void setFbankAccountPersonId(String fbankAccountPersonId) {
		this.fbankAccountPersonId = fbankAccountPersonId;
	}

	@Column(name = "fAddress")
	public String getFaddress() {
		return this.faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	@Column(name = "fGps", length = 255)
	public String getFgps() {
		return this.fgps;
	}

	public void setFgps(String fgps) {
		this.fgps = fgps;
	}

	@Column(name = "fContractEffective")
	public String getFcontractEffective() {
		return this.fcontractEffective;
	}

	public void setFcontractEffective(String fcontractEffective) {
		this.fcontractEffective = fcontractEffective;
	}

	@Column(name = "fRate", precision = 8)
	public BigDecimal getFrate() {
		return this.frate;
	}

	public void setFrate(BigDecimal frate) {
		this.frate = frate;
	}

	@Column(name = "fNoSettlementBalance", precision = 18)
	public BigDecimal getFnoSettlementBalance() {
		return this.fnoSettlementBalance;
	}

	public void setFnoSettlementBalance(BigDecimal fnoSettlementBalance) {
		this.fnoSettlementBalance = fnoSettlementBalance;
	}

	@Column(name = "fFreezeBalance", precision = 18)
	public BigDecimal getFfreezeBalance() {
		return this.ffreezeBalance;
	}

	public void setFfreezeBalance(BigDecimal ffreezeBalance) {
		this.ffreezeBalance = ffreezeBalance;
	}

	@Column(name = "fBalance", precision = 18)
	public BigDecimal getFbalance() {
		return this.fbalance;
	}

	public void setFbalance(BigDecimal fbalance) {
		this.fbalance = fbalance;
	}

	@Column(name = "fBdID")
	public Long getFbdId() {
		return this.fbdId;
	}

	public void setFbdId(Long fbdId) {
		this.fbdId = fbdId;
	}

	@Column(name = "fCreaterId")
	public Long getFcreaterId() {
		return this.fcreaterId;
	}

	public void setFcreaterId(Long fcreaterId) {
		this.fcreaterId = fcreaterId;
	}

	@Column(name = "fCreateTime", length = 19)
	public Date getFcreateTime() {
		return this.fcreateTime;
	}

	public void setFcreateTime(Date fcreateTime) {
		this.fcreateTime = fcreateTime;
	}

	@Column(name = "fUpdateTime", length = 19)
	public Date getFupdateTime() {
		return this.fupdateTime;
	}

	public void setFupdateTime(Date fupdateTime) {
		this.fupdateTime = fupdateTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TEvent> getTEvents() {
		return this.TEvents;
	}

	public void setTEvents(Set<TEvent> TEvents) {
		this.TEvents = TEvents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TOrder> getTOrders() {
		return this.TOrders;
	}

	public void setTOrders(Set<TOrder> TOrders) {
		this.TOrders = TOrders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TOrderVerification> getTOrderVerifications() {
		return this.TOrderVerifications;
	}

	public void setTOrderVerifications(Set<TOrderVerification> TOrderVerifications) {
		this.TOrderVerifications = TOrderVerifications;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TSponsorWithdraw> getTSponsorWithdraws() {
		return this.TSponsorWithdraws;
	}

	public void setTSponsorWithdraws(Set<TSponsorWithdraw> TSponsorWithdraws) {
		this.TSponsorWithdraws = TSponsorWithdraws;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TSponsorBalance> getTSponsorBalances() {
		return this.TSponsorBalances;
	}

	public void setTSponsorBalances(Set<TSponsorBalance> TSponsorBalances) {
		this.TSponsorBalances = TSponsorBalances;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TSponsorStatement> getTSponsorStatements() {
		return this.TSponsorStatements;
	}

	public void setTSponsorStatements(Set<TSponsorStatement> TSponsorStatements) {
		this.TSponsorStatements = TSponsorStatements;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSponsor")
	public Set<TMerchant> getTMerchants() {
		return this.TMerchants;
	}

	public void setTMerchants(Set<TMerchant> TMerchants) {
		this.TMerchants = TMerchants;
	}

}