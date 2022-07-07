package com.pts.motivation.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MoveObject {
	private String id; 
	private String code;
	private String no;
	
	private String cmpnOutId;
	private String cmpnOutName;
	private String deptOutId; 
	private String deptOutName;
	private String dateOut;
	
	private String cmpnInId;
	private String cmpnInName;
	private String deptInId; 
	private String deptInName;
	private String dateIn;
	
	private String reason; 
	
	private String userCreate;
	private String dateCreate;
	private String noteCreate;
	
	private String commentManager; 
	private String userManager;
	private String dateManager;
	private String noteManager;
	
	private String commentAccount;
	private String userAccount;
	private String dateAccount;
	private String noteAccount;
	
	private String commentStock;
	private String userStock;
	private String dateStock;
	private String noteStock;
	
	private String status; 
	private String deleteFg;
	private String cmpnCd;
	
	private String nameAsset; 
	private String modelAsset; 
	private String seriesAsset;
	
	private String dateStart; 
	private String dateEnd;
	
	private MultipartFile fileCongVan;
	
	
	private List<AssetMoveObjectDetail> lstAsset;
	
	
	public MultipartFile getFileCongVan() {
		return fileCongVan;
	}


	public void setFileCongVan(MultipartFile fileCongVan) {
		this.fileCongVan = fileCongVan;
	}


	public MoveObject() {
		lstAsset = new ArrayList<>();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCmpnOutId() {
		return cmpnOutId;
	}
	public void setCmpnOutId(String cmpnOutId) {
		this.cmpnOutId = cmpnOutId;
	}
	public String getCmpnOutName() {
		return cmpnOutName;
	}
	public void setCmpnOutName(String cmpnOutName) {
		this.cmpnOutName = cmpnOutName;
	}
	public String getDeptOutId() {
		return deptOutId;
	}
	public void setDeptOutId(String deptOutId) {
		this.deptOutId = deptOutId;
	}
	public String getDeptOutName() {
		return deptOutName;
	}
	public void setDeptOutName(String deptOutName) {
		this.deptOutName = deptOutName;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getCmpnInId() {
		return cmpnInId;
	}
	public void setCmpnInId(String cmpnInId) {
		this.cmpnInId = cmpnInId;
	}
	public String getCmpnInName() {
		return cmpnInName;
	}
	public void setCmpnInName(String cmpnInName) {
		this.cmpnInName = cmpnInName;
	}
	public String getDeptInId() {
		return deptInId;
	}
	public void setDeptInId(String deptInId) {
		this.deptInId = deptInId;
	}
	public String getDeptInName() {
		return deptInName;
	}
	public void setDeptInName(String deptInName) {
		this.deptInName = deptInName;
	}
	public String getDateIn() {
		return dateIn;
	}
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getNoteCreate() {
		return noteCreate;
	}
	public void setNoteCreate(String noteCreate) {
		this.noteCreate = noteCreate;
	}
	public String getCommentManager() {
		return commentManager;
	}
	public void setCommentManager(String commentManager) {
		this.commentManager = commentManager;
	}
	public String getUserManager() {
		return userManager;
	}
	public void setUserManager(String userManager) {
		this.userManager = userManager;
	}
	public String getDateManager() {
		return dateManager;
	}
	public void setDateManager(String dateManager) {
		this.dateManager = dateManager;
	}
	public String getNoteManager() {
		return noteManager;
	}
	public void setNoteManager(String noteManager) {
		this.noteManager = noteManager;
	}
	public String getCommentAccount() {
		return commentAccount;
	}
	public void setCommentAccount(String commentAccount) {
		this.commentAccount = commentAccount;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getDateAccount() {
		return dateAccount;
	}
	public void setDateAccount(String dateAccount) {
		this.dateAccount = dateAccount;
	}
	public String getNoteAccount() {
		return noteAccount;
	}
	public void setNoteAccount(String noteAccount) {
		this.noteAccount = noteAccount;
	}
	public String getCommentStock() {
		return commentStock;
	}
	public void setCommentStock(String commentStock) {
		this.commentStock = commentStock;
	}
	public String getUserStock() {
		return userStock;
	}
	public void setUserStock(String userStock) {
		this.userStock = userStock;
	}
	public String getDateStock() {
		return dateStock;
	}
	public void setDateStock(String dateStock) {
		this.dateStock = dateStock;
	}
	public String getNoteStock() {
		return noteStock;
	}
	public void setNoteStock(String noteStock) {
		this.noteStock = noteStock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeleteFg() {
		return deleteFg;
	}
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}
	public String getCmpnCd() {
		return cmpnCd;
	}
	public void setCmpnCd(String cmpnCd) {
		this.cmpnCd = cmpnCd;
	}
	public List<AssetMoveObjectDetail> getLstAsset() {
		return lstAsset;
	}
	public void setLstAsset(List<AssetMoveObjectDetail> lstAsset) {
		this.lstAsset = lstAsset;
	}


	public String getNameAsset() {
		return nameAsset;
	}


	public void setNameAsset(String nameAsset) {
		this.nameAsset = nameAsset;
	}


	public String getModelAsset() {
		return modelAsset;
	}


	public void setModelAsset(String modelAsset) {
		this.modelAsset = modelAsset;
	}


	public String getSeriesAsset() {
		return seriesAsset;
	}


	public void setSeriesAsset(String seriesAsset) {
		this.seriesAsset = seriesAsset;
	}


	public String getDateStart() {
		return dateStart;
	}


	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}


	public String getDateEnd() {
		return dateEnd;
	}


	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	
	
}
