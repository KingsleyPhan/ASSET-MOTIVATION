package com.pts.motivation.model;

public class ASSET {
	private String id;
	private String stt; 
	private String name; 
	private String rfid; 
	private String model; 
	private String series;
	private String deptCd;
	private String deptName;
	private String accountCd;
	
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAccountCd() {
		return accountCd;
	}
	public void setAccountCd(String accountCd) {
		this.accountCd = accountCd;
	}
	
	
}
