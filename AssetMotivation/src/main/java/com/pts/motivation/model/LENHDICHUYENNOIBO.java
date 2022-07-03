package com.pts.motivation.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class LENHDICHUYENNOIBO {
	private String id; 
	@NotEmpty
	private String noNumber;
	@NotEmpty
	private String deptOut;
	@NotEmpty
	private String deptIn; 
	@NotEmpty
	private String dateOut;
	private String status; 
	private String sticker;
	private String cmpnCdOut;
	private String cmpnCdIn; 
	private String dateStart; 
	private String dateEndScheduale;
	private String dateEndReal;
	@NotEmpty
	private String reason; 
	private String commentCreate; 
	private String deleteFg;
	private String insertDt; 
	private String updateDt; 
	private String insertUser; 
	private String updateUser;
	private String approvePCDUser; 
	private String approvePCDDt; 
	private String commentPCD;
	private String approveAccountUser; 
	private String approveAccountDt; 
	private String commentAccount; 
	private String approveStockUser; 
	private String approveStockDt; 
	private String commentStock;
	private String cmpnCd;
	private String cmpnInName; 
	private String cmpnOutName;
	private String deptInName; 
	private String deptOutName;
	private List<ASSETDICHUYENNOIBO> lstAsset;
	
	public LENHDICHUYENNOIBO() {
		lstAsset = new ArrayList<ASSETDICHUYENNOIBO>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoNumber() {
		return noNumber;
	}
	public void setNoNumber(String noNumber) {
		this.noNumber = noNumber;
	}
	public String getDeptOut() {
		return deptOut;
	}
	public void setDeptOut(String deptOut) {
		this.deptOut = deptOut;
	}
	public String getDeptIn() {
		return deptIn;
	}
	public void setDeptIn(String deptIn) {
		this.deptIn = deptIn;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSticker() {
		return sticker;
	}
	public void setSticker(String sticker) {
		this.sticker = sticker;
	}
	public String getCmpnCdOut() {
		return cmpnCdOut;
	}
	public void setCmpnCdOut(String cmpnCdOut) {
		this.cmpnCdOut = cmpnCdOut;
	}
	public String getCmpnCdIn() {
		return cmpnCdIn;
	}
	public void setCmpnCdIn(String cmpnCdIn) {
		this.cmpnCdIn = cmpnCdIn;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEndScheduale() {
		return dateEndScheduale;
	}
	public void setDateEndScheduale(String dateEndScheduale) {
		this.dateEndScheduale = dateEndScheduale;
	}
	public String getDateEndReal() {
		return dateEndReal;
	}
	public void setDateEndReal(String dateEndReal) {
		this.dateEndReal = dateEndReal;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCommentCreate() {
		return commentCreate;
	}
	public void setCommentCreate(String commentCreate) {
		this.commentCreate = commentCreate;
	}
	public String getDeleteFg() {
		return deleteFg;
	}
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}
	public String getInsertDt() {
		return insertDt;
	}
	public void setInsertDt(String insertDt) {
		this.insertDt = insertDt;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	public String getInsertUser() {
		return insertUser;
	}
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getApprovePCDUser() {
		return approvePCDUser;
	}
	public void setApprovePCDUser(String approvePCDUser) {
		this.approvePCDUser = approvePCDUser;
	}
	public String getApprovePCDDt() {
		return approvePCDDt;
	}
	public void setApprovePCDDt(String approvePCDDt) {
		this.approvePCDDt = approvePCDDt;
	}
	public String getCommentPCD() {
		return commentPCD;
	}
	public void setCommentPCD(String commentPCD) {
		this.commentPCD = commentPCD;
	}
	public String getApproveAccountUser() {
		return approveAccountUser;
	}
	public void setApproveAccountUser(String approveAccountUser) {
		this.approveAccountUser = approveAccountUser;
	}
	public String getApproveAccountDt() {
		return approveAccountDt;
	}
	public void setApproveAccountDt(String approveAccountDt) {
		this.approveAccountDt = approveAccountDt;
	}
	public String getCommentAccount() {
		return commentAccount;
	}
	public void setCommentAccount(String commentAccount) {
		this.commentAccount = commentAccount;
	}
	public String getApproveStockUser() {
		return approveStockUser;
	}
	public void setApproveStockUser(String approveStockUser) {
		this.approveStockUser = approveStockUser;
	}
	public String getApproveStockDt() {
		return approveStockDt;
	}
	public void setApproveStockDt(String approveStockDt) {
		this.approveStockDt = approveStockDt;
	}
	public String getCommentStock() {
		return commentStock;
	}
	public void setCommentStock(String commentStock) {
		this.commentStock = commentStock;
	}
	public String getCmpnCd() {
		return cmpnCd;
	}
	public void setCmpnCd(String cmpnCd) {
		this.cmpnCd = cmpnCd;
	}
	public String getCmpnInName() {
		return cmpnInName;
	}
	public void setCmpnInName(String cmpnInName) {
		this.cmpnInName = cmpnInName;
	}
	public String getCmpnOutName() {
		return cmpnOutName;
	}
	public void setCmpnOutName(String cmpnOutName) {
		this.cmpnOutName = cmpnOutName;
	}
	public String getDeptInName() {
		return deptInName;
	}
	public void setDeptInName(String deptInName) {
		this.deptInName = deptInName;
	}
	public String getDeptOutName() {
		return deptOutName;
	}
	public void setDeptOutName(String deptOutName) {
		this.deptOutName = deptOutName;
	}

	public List<ASSETDICHUYENNOIBO> getLstAsset() {
		return lstAsset;
	}

	public void setLstAsset(List<ASSETDICHUYENNOIBO> lstAsset) {
		this.lstAsset = lstAsset;
	}
	
	
	
	
	
}
